package com.wuyou.bookCate.bean;

import com.wuyou.base.bean.PaginationBean;

/**
 * BookCateBean page bean.
 * @author Autocode
 * 2022-02-26 23:20:49
 */
public class BookCatePageBean extends BookCateBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
