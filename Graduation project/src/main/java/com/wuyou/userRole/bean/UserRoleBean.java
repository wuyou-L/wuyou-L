package com.wuyou.userRole.bean;
import java.lang.String;
import java.lang.Integer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限
 * @author Autocode
 * 2022-02-26 23:23:31
 */
@ApiModel("权限")
public class UserRoleBean {
	@ApiModelProperty(value="权限id")
	private Integer role_id;

	@ApiModelProperty(value="权限编码")
	private String role_code;

	@ApiModelProperty(value="权限名称")
	private String role_name;

	@ApiModelProperty(value="权限说明")
	private String role_desc;


	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

}
