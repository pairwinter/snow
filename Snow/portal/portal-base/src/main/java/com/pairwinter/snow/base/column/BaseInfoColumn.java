package com.pairwinter.snow.base.column;

/**
 * BaseInfoColumn.<br/>
 *
 * @author bin (bin@hypersun.com)
 * @since  May 31, 2012 
 */
public enum BaseInfoColumn
{
	ID("id"),
	id("_id"),
    name("name"),
    accountId("accountId"),
	status("status"),
	createdId("createdId"),
	createdName("createdName"),
	lastModifiedId("lastModifiedId"),
	lastModifiedName("lastModifiedName"),
	createdDate("createdDate"),
	lastModifiedDate("lastModifiedDate"),
    organizationId("organizationId"),
    createdProxyName("createdProxyName"),
    lastModifiedProxyName("lastModifiedProxyName"),
	resourceBundleId("resourceBundleId"),
    contactOrgId("contactOrgId"),
    contactId("contactId");
	private String columnName;

	private BaseInfoColumn(String columnName) {
		this.columnName = columnName;
	}

	public String getName() {
		return columnName;
	}
}
