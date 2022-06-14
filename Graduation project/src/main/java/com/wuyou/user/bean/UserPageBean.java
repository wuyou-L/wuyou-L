package com.wuyou.user.bean;

import com.wuyou.base.bean.PaginationBean;

/**
 * UserBean page bean.
 * @author Autocode
 * 2022-02-26 23:22:43
 */
public class UserPageBean extends UserBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
