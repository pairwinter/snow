package com.pairwinter.snow.utils.datapage;

/**
 * OrderByHelper.<br/>
 * 
 * @author tim
 * @since Apr 26, 2012
 */
public class OrderBy {
	
	private String	columnName;
	private OrderDirection orderDirection ;
    private Class<?> clazz;
    private String tableName;
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public OrderDirection getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(OrderDirection orderDirection) {
		this.orderDirection = orderDirection;
	}

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public enum OrderDirection {
		ASC, DESC;
	}
	
	public OrderBy() {
		super();
	}

    public OrderBy(String columnName, OrderDirection orderDirection) {
        super();
        this.columnName = columnName;
        this.orderDirection = orderDirection;
    }

	public OrderBy(String tableName, String columnName, OrderDirection orderDirection) {
		super();
        this.tableName = tableName;
		this.columnName = columnName;
		this.orderDirection = orderDirection;
	}
}
