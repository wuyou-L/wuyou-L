package com.wuyou.bookBorrow.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 借阅信息
 * @author Autocode
 * 2022-02-26 23:19:40
 */
@ApiModel("借阅信息")
public class BookBorrowBean {
	@ApiModelProperty(value="借阅信息id")
	private Integer borrow_id;

	@ApiModelProperty(value="用户id")
	private Integer borrow_user_id;

	private String borrow_user_name;
	@ApiModelProperty(value="图书id")
	private Integer borrow_book_id;

	private String borrow_book_name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GTM+8")
	@ApiModelProperty(value="借书日期")
	private Timestamp borrow_time_create;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GTM+8")
	private Timestamp borrow_time_create_end;
	@ApiModelProperty(value="还书日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GTM+8")
	private Timestamp borrow_time_end;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GTM+8")
	private Timestamp borrow_time_end_end;

	@ApiModelProperty(value="管理员账号")
	private String borrow_admin_account;

	@ApiModelProperty(value="借阅状态")
	private Integer borrow_status;

	@ApiModelProperty(value="还书类型")
	private Integer borrow_return_type;


	public Integer getBorrow_id() {
		return borrow_id;
	}
	public void setBorrow_id(Integer borrow_id) {
		this.borrow_id = borrow_id;
	}
	public Integer getBorrow_user_id() {
		return borrow_user_id;
	}
	public void setBorrow_user_id(Integer borrow_user_id) {
		this.borrow_user_id = borrow_user_id;
	}
	public Integer getBorrow_book_id() {
		return borrow_book_id;
	}
	public void setBorrow_book_id(Integer borrow_book_id) {
		this.borrow_book_id = borrow_book_id;
	}
	public Timestamp getBorrow_time_create() {
		return borrow_time_create;
	}
	public void setBorrow_time_create(Timestamp borrow_time_create) {
		this.borrow_time_create = borrow_time_create;
	}
	public Timestamp getBorrow_time_end() {
		return borrow_time_end;
	}
	public void setBorrow_time_end(Timestamp borrow_time_end) {
		this.borrow_time_end = borrow_time_end;
	}
	public String getBorrow_admin_account() {
		return borrow_admin_account;
	}
	public void setBorrow_admin_account(String borrow_admin_account) {
		this.borrow_admin_account = borrow_admin_account;
	}
	public Integer getBorrow_status() {
		return borrow_status;
	}
	public void setBorrow_status(Integer borrow_status) {
		this.borrow_status = borrow_status;
	}
	public Integer getBorrow_return_type() {
		return borrow_return_type;
	}
	public void setBorrow_return_type(Integer borrow_return_type) {
		this.borrow_return_type = borrow_return_type;
	}


	public String getBorrow_user_name() {
		return borrow_user_name;
	}

	public void setBorrow_user_name(String borrow_user_name) {
		this.borrow_user_name = borrow_user_name;
	}

	public String getBorrow_book_name() {
		return borrow_book_name;
	}

	public void setBorrow_book_name(String borrow_book_name) {
		this.borrow_book_name = borrow_book_name;
	}

	public Timestamp getBorrow_time_create_end() {
		return borrow_time_create_end;
	}

	public void setBorrow_time_create_end(Timestamp borrow_time_create_end) {
		this.borrow_time_create_end = borrow_time_create_end;
	}

	public Timestamp getBorrow_time_end_end() {
		return borrow_time_end_end;
	}

	public void setBorrow_time_end_end(Timestamp borrow_time_end_end) {
		this.borrow_time_end_end = borrow_time_end_end;
	}
}
