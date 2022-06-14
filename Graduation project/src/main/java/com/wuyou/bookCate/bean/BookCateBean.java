package com.wuyou.bookCate.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * 分类
 * @author Autocode
 * 2022-02-26 23:20:49
 */
@ApiModel("分类")
public class BookCateBean {
	@ApiModelProperty(value="分类id")
	private Integer cate_id;

	@ApiModelProperty(value="分类编码")
	private String cate_code;

	@ApiModelProperty(value="分类名称")
	private String cate_name;

	@ApiModelProperty(value="父分类编码")
	private String cate_parent_code;

	@ApiModelProperty(value="父分类名称")
	private String cate_parent_name;

	@ApiModelProperty(value="图片路径")
	private String cate_img_url;

	@ApiModelProperty(value="图片名称")
	private String cate_img_name;

	@ApiModelProperty(value="分类创建账号")
	private String cate_create_account;

	@ApiModelProperty(value="创建时间")
	private Timestamp cate_create_time;

	private List<BookCateBean> bookCateBeanList;

	public List<BookCateBean> getBookCateBeanList() {
		return bookCateBeanList;
	}

	private Integer is_using;

	public Integer getIs_using() {
		return is_using;
	}

	public void setIs_using(Integer is_using) {
		this.is_using = is_using;
	}

	public void setBookCateBeanList(List<BookCateBean> bookCateBeanList) {
		this.bookCateBeanList = bookCateBeanList;
	}

	public Integer getCate_id() {
		return cate_id;
	}
	public void setCate_id(Integer cate_id) {
		this.cate_id = cate_id;
	}
	public String getCate_code() {
		return cate_code;
	}
	public void setCate_code(String cate_code) {
		this.cate_code = cate_code;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getCate_parent_code() {
		return cate_parent_code;
	}
	public void setCate_parent_code(String cate_parent_code) {
		this.cate_parent_code = cate_parent_code;
	}
	public String getCate_parent_name() {
		return cate_parent_name;
	}
	public void setCate_parent_name(String cate_parent_name) {
		this.cate_parent_name = cate_parent_name;
	}
	public String getCate_img_url() {
		return cate_img_url;
	}
	public void setCate_img_url(String cate_img_url) {
		this.cate_img_url = cate_img_url;
	}
	public String getCate_img_name() {
		return cate_img_name;
	}
	public void setCate_img_name(String cate_img_name) {
		this.cate_img_name = cate_img_name;
	}
	public String getCate_create_account() {
		return cate_create_account;
	}
	public void setCate_create_account(String cate_create_account) {
		this.cate_create_account = cate_create_account;
	}
	public Timestamp getCate_create_time() {
		return cate_create_time;
	}
	public void setCate_create_time(Timestamp cate_create_time) {
		this.cate_create_time = cate_create_time;
	}

}
