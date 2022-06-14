package com.wuyou.userRoleMapping.bean;
import com.wuyou.base.bean.PaginationBean;

/**
 * UserRoleMappingBean page bean.
 * @author Autocode
 * 2022-03-23 11:20:27
 */
public class UserRoleMappingPageBean extends UserRoleMappingBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
