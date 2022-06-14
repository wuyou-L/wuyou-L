package com.wuyou.sysModule.bean;

import com.wuyou.base.bean.PaginationBean;

/**
 * SysModuleBean page bean.
 * @author Autocode
 * 2022-03-23 11:23:04
 */
public class SysModulePageBean extends SysModuleBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
