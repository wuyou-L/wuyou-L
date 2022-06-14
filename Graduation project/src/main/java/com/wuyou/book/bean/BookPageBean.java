package com.wuyou.book.bean;

import com.wuyou.base.bean.PaginationBean;

/**
 * BookBean page bean.
 * @author Autocode
 * 2022-02-26 23:14:51
 */
public class BookPageBean extends BookBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
