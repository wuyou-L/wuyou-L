package com.wuyou.user.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 用户
 * @author Autocode
 * 2022-02-26 23:22:43
 */
@ApiModel("用户")
public class UserBean {
	@ApiModelProperty(value = "用户id")
	private Integer user_id;

	@ApiModelProperty(value = "账号")
	private String user_account;

	@ApiModelProperty(value = "用户密码")
	private String user_pwd;

	@ApiModelProperty(value = "姓名")
	private String user_name;

	@ApiModelProperty(value = "性别")
	private String user_sex;

	@ApiModelProperty(value = "证件号")
	private String user_card_no;

	@ApiModelProperty(value = "类别（0：普通用户，1：管理员）")
	private Integer user_type;

	@ApiModelProperty(value = "备注")
	private String user_remark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8", locale = "zh")
	@ApiModelProperty(value = "创建时间")
	private Timestamp user_time_create;

	@ApiModelProperty(value = "用户图片名")
	private String user_img_name;

	@ApiModelProperty(value = "用户图片路径")
	private String user_img_url;

	@ApiModelProperty(value = "权限编码")
	private String user_role_code;

	@ApiModelProperty(value = "权限名称")
	private String user_role_name;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8", locale = "zh")
	private Timestamp user_time_end;

	public Timestamp getUser_time_end() {
		return user_time_end;
	}

	public void setUser_time_end(Timestamp user_time_end) {
		this.user_time_end = user_time_end;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_card_no() {
		return user_card_no;
	}

	public void setUser_card_no(String user_card_no) {
		this.user_card_no = user_card_no;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public String getUser_remark() {
		return user_remark;
	}

	public void setUser_remark(String user_remark) {
		this.user_remark = user_remark;
	}

	public Timestamp getUser_time_create() {
		return user_time_create;
	}

	public void setUser_time_create(Timestamp user_time_create) {
		this.user_time_create = user_time_create;
	}

	public String getUser_img_name() {
		return user_img_name;
	}

	public void setUser_img_name(String user_img_name) {
		this.user_img_name = user_img_name;
	}

	public String getUser_img_url() {
		return user_img_url;
	}

	public void setUser_img_url(String user_img_url) {
		this.user_img_url = user_img_url;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public String getUser_role_name() {
		return user_role_name;
	}

	public void setUser_role_name(String user_role_name) {
		this.user_role_name = user_role_name;
	}

	@Override
	public String toString() {
		return "UserBean{" +
				"user_id=" + user_id +
				", user_account='" + user_account + '\'' +
				", user_pwd='" + user_pwd + '\'' +
				", user_name='" + user_name + '\'' +
				", user_sex='" + user_sex + '\'' +
				", user_card_no='" + user_card_no + '\'' +
				", user_type=" + user_type +
				", user_remark='" + user_remark + '\'' +
				", user_time_create=" + user_time_create +
				", user_img_name='" + user_img_name + '\'' +
				", user_img_url='" + user_img_url + '\'' +
				", user_role_code='" + user_role_code + '\'' +
				", user_role_name='" + user_role_name + '\'' +
				", user_time_end=" + user_time_end +
				'}';
	}
}