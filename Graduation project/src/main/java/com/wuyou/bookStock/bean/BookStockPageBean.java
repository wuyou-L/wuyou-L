package com.wuyou.bookStock.bean;

import com.wuyou.base.bean.PaginationBean;

/**
 * BookStockBean page bean.
 * @author Autocode
 * 2022-02-26 23:21:47
 */
public class BookStockPageBean extends BookStockBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
