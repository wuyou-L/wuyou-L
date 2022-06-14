package com.wuyou.bookBorrow.bean;

import com.wuyou.base.bean.PaginationBean;

/**
 * BookBorrowBean page bean.
 * @author Autocode
 * 2022-02-26 23:19:40
 */
public class BookBorrowPageBean extends BookBorrowBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
