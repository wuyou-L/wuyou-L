package com.wuyou.userRoleMapping.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限和功能映射
 * @author Autocode
 * 2022-03-23 11:20:27
 */
@ApiModel("权限和功能映射")
public class UserRoleMappingBean {
	@ApiModelProperty(value="角色id")
	private Integer role_code;

	@ApiModelProperty(value="功能编码")
	private String mod_code;

	@ApiModelProperty(value="扩展字段1")
	private String ext_col1;

	@ApiModelProperty(value="扩展字段2")
	private String ext_col2;

	@ApiModelProperty(value="扩展字段3")
	private String ext_col3;


	public Integer getRole_code() {
		return role_code;
	}
	public void setRole_code(Integer role_code) {
		this.role_code = role_code;
	}
	public String getMod_code() {
		return mod_code;
	}
	public void setMod_code(String mod_code) {
		this.mod_code = mod_code;
	}
	public String getExt_col1() {
		return ext_col1;
	}
	public void setExt_col1(String ext_col1) {
		this.ext_col1 = ext_col1;
	}
	public String getExt_col2() {
		return ext_col2;
	}
	public void setExt_col2(String ext_col2) {
		this.ext_col2 = ext_col2;
	}
	public String getExt_col3() {
		return ext_col3;
	}
	public void setExt_col3(String ext_col3) {
		this.ext_col3 = ext_col3;
	}

}
