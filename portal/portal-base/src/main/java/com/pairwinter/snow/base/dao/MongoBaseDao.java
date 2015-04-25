package com.pairwinter.snow.base.dao;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.pairwinter.snow.base.column.BaseInfoColumn;
import com.pairwinter.snow.base.enumerably.Status;
import com.pairwinter.snow.exception.BaseException;
import com.pairwinter.snow.utils.clazz.GenericsUtils;
import com.pairwinter.snow.utils.datapage.DataPage;
import com.pairwinter.snow.utils.datapage.OrderBy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * MongoBaseDao.<br/>
 * 
 * @author bin (bin@hypersun.com)
 * @since Apr 27, 2012
 */
public class MongoBaseDao<T> {

    protected int appServerId = 51;
    protected int databaseId = 2;
    protected static long MAX_ID_MASK = 0xffffffffL;
    protected static long MIN_ID_MASK = 0;
    protected Method idMethod;
    //private static final String idName = "setId";
    protected Class<T> entityClass;

    @Autowired
    private MongoTemplate mongoTemplate;

    protected AtomicLong currentId = new AtomicLong(-1L);
    private static final Logger logger = LoggerFactory.getLogger(MongoBaseDao.class);

    private static int countter = 1;
    @SuppressWarnings("unchecked")
    public MongoBaseDao() {
        entityClass = GenericsUtils.getSuperClassGenricType(getClass());
        try {
            entityClass.getMethods();
        } catch (Exception e) {
            logger.error("Set Id not found", e);

        }
    }

    public void addOrderToQuery(Query query, List<OrderBy> orderBys) {
        if (null != query) {
            if (null != orderBys && orderBys.size() != 0) {
                for (OrderBy orderBy : orderBys) {
                    if (orderBy.getOrderDirection() == OrderBy.OrderDirection.DESC) {
                        query.sort().on(orderBy.getColumnName(), Order.DESCENDING);
                    } else {
                        query.sort().on(orderBy.getColumnName(), Order.ASCENDING);
                    }
                }
            }
        }
    }

    /**
     * DataPage.<br>
     * If pageNo or pageSize is null, return all data.
     * 
     * @param query
     * @param pageNo
     * @param pageSize
     * @return DataPage<T>
     */
    public DataPage<T> pagedQuery(Query query, Integer pageNo, Integer pageSize, List<OrderBy> orderBys) {
    	return pagedQuery(query, pageNo, pageSize, orderBys, null);
    }
    
    public DataPage<T> pagedQuery(Query query, Integer pageNo, Integer pageSize, List<OrderBy> orderBys, Long totalCount) {
    	  if (null == query) {
              query = new Query();
          }
          addOrderToQuery(query, orderBys);

          if (pageNo == null || pageSize == null) {
              List<T> list = mongoTemplate.find(query, entityClass);
              if (null == list || list.size() == 0) {
                  return new DataPage<T>();
              } else {
                  return new DataPage<T>(0, list.size(), list.size(), list);
              }
          } else {
              Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
              long count = 0;
              if(totalCount == null){
            	  
            	  count = mongoTemplate.count(query, entityClass);
              }else{
            	  count = totalCount;
              }

              if (count < 1) {
                  return new DataPage<T>();
              }
              int startIndex = DataPage.getStartOfPage(pageNo, pageSize);

              query = query.skip(startIndex).limit(pageSize);
              List<T> list = mongoTemplate.find(query, entityClass);
              return new DataPage<T>(startIndex, count, pageSize, list);
          }
    }

    /**
     * Get primary key.<br>
     * You can set primarykeyCol as your collection primary key. If
     * primarykeyCol is null , default set primary key name as _id or you can
     * invoke getNextId() function to get next id.
     * 
     *            column name
     * @return primary key
     */
    public synchronized long getNextId(Class<T> clazz, String primarykeyCol) {
        if (currentId.get() == -1) {
            if (StringUtils.isEmpty(primarykeyCol)) {
                primarykeyCol = "_id";
            }
            int newAppServerId = (appServerId << 10);
            long nodeIdMask = newAppServerId | databaseId;
            nodeIdMask = (nodeIdMask << 32);
            long maxId = MAX_ID_MASK | nodeIdMask;
            long minId = nodeIdMask;
            logger.debug("AppServerId: "+appServerId+"; DataBaseId: "+databaseId);
            Query query = new Query();
            query.fields().include(primarykeyCol);
            query.addCriteria(new Criteria(primarykeyCol).gt(minId).lt(maxId));
            query.sort().on(primarykeyCol, Order.DESCENDING);

            Object object = null;
            try {
                // String sql =
                // query.getHint()+"---"+query.getQueryObject().toString();
                object = mongoTemplate.findOne(query.limit(1), clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (object != null) {

                String getIdMethodName = null;

                try {
                    if (primarykeyCol.equals("_id")) {
                        getIdMethodName = "getId";
                    } else {
                        getIdMethodName = "get" + StringUtils.capitalize(primarykeyCol);
                    }
                    Method m = clazz.getMethod(getIdMethodName);
                    currentId.set(Long.valueOf(String.valueOf(m.invoke(object))));
                } catch (Exception e) {
                    logger.error("Cannot invoke get primary key by function {}.", new Object[] { getIdMethodName });
                    throw new BaseException("Cannot invoke get primary key by function getIdMethodName");
                }
            } else {
                currentId.set(minId);
            }
        }
        return currentId.incrementAndGet();
    }

    /**
     * Get primary key.<br>
     * If primary key name is _id, then you can invoke the function to get next
     * id.
     * 
     * @return primary key
     */
    public long getNextId(Class<T> clazz) {
        return getNextId(clazz, null);
    }

    public long getNextId() {
        return getNextId(entityClass);
    }

    public Criteria getlikeCriteriaItem(Criteria criteria, String columnName, String reg, boolean ingoreBlank) {
        if (null != reg && (!StringUtils.isBlank(reg) || (StringUtils.isBlank(reg) && !ingoreBlank))) {
            if (null != criteria) {
                criteria.and(columnName).regex(reg);
            } else {
                criteria = Criteria.where(columnName).regex(reg);
            }
        }
        return criteria;
    }

    public void addToListCriterias(List<Criteria> Criterias, Criteria item) {
        if (null != item) {
            Criterias.add(item);
        }
    }

    public T getById(BigInteger id) {
        return getMongoTemplate().findById(id, entityClass);
    }
        
    public T getByIdAndStatus(BigInteger id, Status    status) {
		Criteria criteria = Criteria.where(BaseInfoColumn.id.getName()).is(id);
		criteria.and(BaseInfoColumn.status.getName()).is(status.getValue());
    	return getMongoTemplate().findOne(new Query(criteria), entityClass);
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    public DataPage<T> findAll(Integer pageNo, Integer pageSize, List<OrderBy> orderBys) {
        return this.pagedQuery(new Query(), pageNo, pageSize, orderBys);
    }

    /**
     * all the methods for remove entity should use id or ids as the parameters
     * @param id
     */
    public void deleteById(Object id) {
        mongoTemplate.remove(new Query(SearchType.E.createQuery(BaseInfoColumn.id.getName(),id)),entityClass);
    }

    /**
     * delete entity after find it with the entity id.
     * this method contain one search by id.
     * @param id
     */
    public void deleteAfterFind(Object id) {
        mongoTemplate.remove(mongoTemplate.findById(id, entityClass));
    }

    public void deleteWithBatch(Object ids){
        mongoTemplate.remove(new Query(SearchType.IN.createQuery(BaseInfoColumn.id.getName(),ids)),entityClass);
    }

    public void deleteAll() {
        mongoTemplate.remove(new Query(), entityClass);
    }
    public void dropCollection() {
        mongoTemplate.dropCollection(entityClass);
    }

    public void insert(T entity) {

        mongoTemplate.insert(entity);
    }
    
    public void save(T entity){
    	mongoTemplate.save(entity);
    }

    public void insert(Collection<? extends Object> batchToSave) {

        mongoTemplate.insert(batchToSave, entityClass);
    }

    public WriteResult updateFirst(Query query, Update update) {
        return mongoTemplate.updateFirst(query, update, entityClass);
    }

    public WriteResult updateMulti(Query query, Update update) {
        return mongoTemplate.updateMulti(query, update, entityClass);
    }

    public WriteResult upsert(Query query, Update update, Class<?> entityClass) {
        return mongoTemplate.upsert(query, update, entityClass);
    }

    public T findOne(Query query) {
        return mongoTemplate.findOne(query, entityClass);
    }

    public List<T> find(Query query) {
        return mongoTemplate.find(query, entityClass);
    }

    public T findById(Object id) {
        return mongoTemplate.findById(id, entityClass);
    }

    public T findAndModify(Query query, Update update) {
        return mongoTemplate.findAndModify(query, update, entityClass);
    }

    public T findAndModify(Query query, Update update, FindAndModifyOptions options) {
        return mongoTemplate.findAndModify(query, update, options, entityClass);
    }

    public T findAndRemove(Query query) {
        return mongoTemplate.findAndRemove(query, entityClass);
    }

    public long count(Query query) {
        return mongoTemplate.count(query, entityClass);
    }

    public long countAll() {
        return mongoTemplate.count(new Query(), entityClass);
    }

    public void remove(Object object) {

        mongoTemplate.remove(object);
    }

    /**
     * make Order For Query
     * 
     * @param query
     * @param orderBys
     */
    public void makeOrderForQuery(Query query, List<OrderBy> orderBys) {
        if (null != orderBys && orderBys.size() != 0) {
            for (OrderBy orderBy : orderBys) {
                if (orderBy.getOrderDirection() == OrderBy.OrderDirection.DESC) {
                    query.sort().on(orderBy.getColumnName(), Order.DESCENDING);
                } else {
                    query.sort().on(orderBy.getColumnName(), Order.ASCENDING);
                }
            }
        }
    }

    public Criteria getCriteria(String columnName, Object columnValue, SearchType searchType) {
        if (null == columnValue) {
            return null;
        }
        return searchType.createQuery(columnName, columnValue);
    }
    
    protected void addResourceBundleIdCriteria(Long resourceBundleId, Criteria criteria) {
        if (resourceBundleId != null) {
            criteria.and(BaseInfoColumn.resourceBundleId.getName()).is(resourceBundleId);
        }
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}