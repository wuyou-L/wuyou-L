package com.wuyou.fileInfo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 文件信息
 * @author Autocode
 * 2022-04-12 20:11:58
 */
@ApiModel("文件信息")
public class FileInfoBean {
	@ApiModelProperty(value="文件信息id")
	private BigInteger id;

	@ApiModelProperty(value="文件名")
	private String file_name;

	@ApiModelProperty(value="文件路径")
	private String file_url;

	@ApiModelProperty(value="访问域名")
	private String file_domain_url;

	@ApiModelProperty(value="文件类别（0：用户， 1：图书）")
	private Integer file_kind;

	@ApiModelProperty(value="录入时间")
	private Timestamp createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8", locale = "zh")
	@ApiModelProperty(value="关联id")
	private Integer rel_id;


	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getFile_domain_url() {
		return file_domain_url;
	}
	public void setFile_domain_url(String file_domain_url) {
		this.file_domain_url = file_domain_url;
	}
	public Integer getFile_kind() {
		return file_kind;
	}
	public void setFile_kind(Integer file_kind) {
		this.file_kind = file_kind;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getRel_id() {
		return rel_id;
	}
	public void setRel_id(Integer rel_id) {
		this.rel_id = rel_id;
	}

}
