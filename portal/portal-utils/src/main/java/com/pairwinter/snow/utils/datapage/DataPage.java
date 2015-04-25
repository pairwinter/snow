package com.pairwinter.snow.utils.datapage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T>
 */
public class DataPage<T> implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	private static int			DEFAULT_PAGE_SIZE	= 10;
	
	private int					pageSize			= DEFAULT_PAGE_SIZE;
	private int					start;
	private List<T>				data;
	private long				totalCount;
	
	public DataPage() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
	}

    public DataPage(Integer pageSize) {
        if (null == pageSize) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

	public DataPage(int start, long totalSize, int pageSize, List<T> data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}
	
	public long getTotalCount() {
		return this.totalCount;
	}
	
	public int getTotalPageCount() {
	   if (0 == this.pageSize) return 1;	
		return DataPage.getTotalPageCount(this.totalCount, this.pageSize);
	}
	
	@SuppressWarnings("unused")
	private void setTotalPageCount(int pageNo) {
		// this is a noop to enable json serialization
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public List<T> getData() {
		return data;
	}
	
	public int getCurrentPageNo() {
      if (0 == this.pageSize) return 1;
		return start / pageSize + 1;
	}
	
	@SuppressWarnings("unused")
	private void setCurrentPageNo(int pageNo) {
		// this is a noop to enable json serialization
	}
	
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}
	
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}
	
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}
	
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	@Override
	public String toString() {
		return "DataPage [pageSize=" + pageSize + ", start=" + start + ", data=" + data + ", totalCount=" + totalCount + "]";
	}
	
	public static int getTotalPageCount(long totalCount, int pageSize) {
		if(totalCount % pageSize == 0)
			return Integer.valueOf(String.valueOf(totalCount / pageSize));
		else
			return Integer.valueOf(String.valueOf(totalCount / pageSize + 1));
	}
}
