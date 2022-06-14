package com.wuyou.fileInfo.bean;

import com.wuyou.base.bean.PaginationBean;

/**
 * FileInfoBean page bean.
 * @author Autocode
 * 2022-04-12 20:11:58
 */
public class FileInfoPageBean extends FileInfoBean{

	private PaginationBean page = null;

	public PaginationBean getPage() {return page;}

	public void setPage(PaginationBean page) { this.page = page;}

}
