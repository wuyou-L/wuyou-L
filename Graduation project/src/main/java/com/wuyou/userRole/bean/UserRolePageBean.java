package com.wuyou.userRole.bean;

import com.wuyou.base.bean.PaginationBean;

/**
 * UserRoleBean page bean.
 * @author Autocode
 * 2022-02-26 23:23:31
 */
public class UserRolePageBean extends UserRoleBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
