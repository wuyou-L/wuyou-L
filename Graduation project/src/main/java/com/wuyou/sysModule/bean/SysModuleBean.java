package com.wuyou.sysModule.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 系统功能模块
 * @author Autocode
 * 2022-03-23 11:23:04
 */
@ApiModel("系统功能模块")
public class SysModuleBean {
	@ApiModelProperty(value="id")
	private Integer id;

	@ApiModelProperty(value="系统编码")
	private String sys_code;

	@ApiModelProperty(value="系统名")
	private String sys_name;

	@ApiModelProperty(value="模块编码")
	private String mod_code;

	@ApiModelProperty(value="模块名")
	private String mod_name;

	@ApiModelProperty(value="模块描述")
	private String mod_desc;

	@ApiModelProperty(value="父级模块编码")
	private String mod_parent_code;

	@ApiModelProperty(value="父级模块名")
	private String mod_parent_name;

	@ApiModelProperty(value="备注")
	private String mod_ramark;

	private List<SysModuleBean> sysModuleBeans;
private Integer is_using;

	public Integer getIs_using() {
		return is_using;
	}

	public void setIs_using(Integer is_using) {
		this.is_using = is_using;
	}

	public List<SysModuleBean> getSysModuleBeans() {
		return sysModuleBeans;
	}

	public void setSysModuleBeans(List<SysModuleBean> sysModuleBeans) {
		this.sysModuleBeans = sysModuleBeans;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSys_code() {
		return sys_code;
	}
	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getMod_code() {
		return mod_code;
	}
	public void setMod_code(String mod_code) {
		this.mod_code = mod_code;
	}
	public String getMod_name() {
		return mod_name;
	}
	public void setMod_name(String mod_name) {
		this.mod_name = mod_name;
	}
	public String getMod_desc() {
		return mod_desc;
	}
	public void setMod_desc(String mod_desc) {
		this.mod_desc = mod_desc;
	}
	public String getMod_parent_code() {
		return mod_parent_code;
	}
	public void setMod_parent_code(String mod_parent_code) {
		this.mod_parent_code = mod_parent_code;
	}
	public String getMod_parent_name() {
		return mod_parent_name;
	}
	public void setMod_parent_name(String mod_parent_name) {
		this.mod_parent_name = mod_parent_name;
	}
	public String getMod_ramark() {
		return mod_ramark;
	}
	public void setMod_ramark(String mod_ramark) {
		this.mod_ramark = mod_ramark;
	}

	@Override
	public String toString() {
		return "SysModuleBean{" +
				"id=" + id +
				", sys_code='" + sys_code + '\'' +
				", sys_name='" + sys_name + '\'' +
				", mod_code='" + mod_code + '\'' +
				", mod_name='" + mod_name + '\'' +
				", mod_desc='" + mod_desc + '\'' +
				", mod_parent_code='" + mod_parent_code + '\'' +
				", mod_parent_name='" + mod_parent_name + '\'' +
				", mod_ramark='" + mod_ramark + '\'' +
				", sysModuleBeans=" + sysModuleBeans +
				'}';
	}
}
