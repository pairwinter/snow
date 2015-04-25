package com.pairwinter.snow.base.dao;

import com.pairwinter.snow.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.geo.Shape;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MongoOperation.<br/>
 * 
 * @author bin (bin@hypersun.com)
 * @since May 14, 2012
 */
public enum SearchType {
	
	E {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).is(columnValue);
		}
	},
	LIKE {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).regex((String) columnValue);
		}
	},
	
	NE {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).ne(columnValue);
		}
	},
	
	GT {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).gt(columnValue);
		}
	},
	
	LT {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).lt(columnValue);
		}
	},
	
	GTE {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).gte(columnValue);
		}
	},
	
	LTE {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).lte(columnValue);
		}
	},
	
	IN {
		@SuppressWarnings("unchecked" )
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			if(columnValue instanceof List){
				ArrayList<Object> values = null;
				try{
					values = (ArrayList<Object>) columnValue;
				}
				catch (Exception e){
					logger.error("createQuery _IN --> {}", e.getCause());
					throw new BaseException();
				}
				return Criteria.where(columnName).in(values);
			}
			else{
				return Criteria.where(columnName).in(columnValue);
			}
			
		}
	},
	
	NEIN {
		@SuppressWarnings("unchecked" )
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			ArrayList<Object> values = null;
			try{
				values = (ArrayList<Object>) columnValue;
			}
			catch (Exception e){
				logger.error("createQuery _NEIN --> {}", e.getCause());
				throw new BaseException();
			}
			return Criteria.where(columnName).in(values).not();
		}
	},
	
	WITHIN {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).within((Shape) columnValue);
		}
	},
	
	TIMEPERIOD {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			Criteria timeCriteria = null;
			Date dateBegin = null;
			Date dateEnd = null;
			try{
				@SuppressWarnings("unchecked" )
				ArrayList<Object> values = (ArrayList<Object>) columnValue;
				if(null == values || values.size() != 2){
					logger.error("createQuery _TIMEPERIOD --> Param must be 2 size list.");
					throw new BaseException();
				}
				dateBegin = (Date) values.get(0);
				dateEnd = (Date) values.get(1);
			}
			catch (Exception e){
				logger.error("createQuery _TIMEPERIOD --> {}", e.getCause());
				throw new BaseException();
			}
			
			if(dateBegin != null && dateEnd != null){
				timeCriteria = Criteria.where(columnName).gte(dateBegin).lte(dateEnd);
			}
			else if(dateBegin != null){
				timeCriteria = Criteria.where(columnName).gte(dateBegin);
			}
			else if(dateEnd != null){
				timeCriteria = Criteria.where(columnName).gte(dateEnd);
			}
			return timeCriteria;
		}
	},
	ALL {
		@Override
		public Criteria createQuery(String columnName, Object columnValue) {
			return Criteria.where(columnName).all(columnValue);
		}
	};
	
	private static final Logger	logger	= LoggerFactory.getLogger(SearchType.class);
	
	public abstract Criteria createQuery(String columnName, Object columnValue);
}
