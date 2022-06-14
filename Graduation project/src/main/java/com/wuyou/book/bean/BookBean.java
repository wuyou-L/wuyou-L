package com.wuyou.book.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuyou.fileInfo.bean.FileInfoBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * 图书
 * @author Autocode
 * 2022-02-26 23:14:51
 */
@ApiModel("图书")
public class BookBean {
	@ApiModelProperty(value="图书id")
	private Integer book_id;

	@ApiModelProperty(value="ISBN码，书号")
	private String book_isbn;

	@ApiModelProperty(value="图书名称")
	private String book_name;

	@ApiModelProperty(value="图书英文名称")
	private String book_name_english;

	@ApiModelProperty(value="出版社名称")
	private String book_publisher;

	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GTM+8")
	@ApiModelProperty(value="出版日期")
	private Timestamp book_publisher_time;

	@ApiModelProperty(value="分类编码")
	private String book_kind_code;

	@ApiModelProperty(value="分类名称")
	private String book_kind_name;

	@ApiModelProperty(value="作者")
	private String book_author;

	@ApiModelProperty(value="单价")
	private Double book_price;

	@ApiModelProperty(value="备注")
	private String book_remark;

	@ApiModelProperty(value="图书图片名")
	private String book_img_name;

	@ApiModelProperty(value="图书图片路径")
	private String book_img_url;

	@ApiModelProperty(value="图书状态")
	private Integer book_status;

	private Timestamp book_create_time;
	private String book_desc;

	public String getBook_desc() {
		return book_desc;
	}

	public void setBook_desc(String book_desc) {
		this.book_desc = book_desc;
	}

	private List<FileInfoBean> fileInfoBeans;
	public Timestamp getBook_create_time() {
		return book_create_time;
	}

	public void setBook_create_time(Timestamp book_create_time) {
		this.book_create_time = book_create_time;
	}

	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public String getBook_isbn() {
		return book_isbn;
	}
	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_name_english() {
		return book_name_english;
	}
	public void setBook_name_english(String book_name_english) {
		this.book_name_english = book_name_english;
	}
	public String getBook_publisher() {
		return book_publisher;
	}
	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public Timestamp getBook_publisher_time() {
		return book_publisher_time;
	}

	public void setBook_publisher_time(Timestamp book_publisher_time) {
		this.book_publisher_time = book_publisher_time;
	}

	public String getBook_kind_code() {
		return book_kind_code;
	}
	public void setBook_kind_code(String book_kind_code) {
		this.book_kind_code = book_kind_code;
	}
	public String getBook_kind_name() {
		return book_kind_name;
	}
	public void setBook_kind_name(String book_kind_name) {
		this.book_kind_name = book_kind_name;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public Double getBook_price() {
		return book_price;
	}
	public void setBook_price(Double book_price) {
		this.book_price = book_price;
	}
	public String getBook_remark() {
		return book_remark;
	}
	public void setBook_remark(String book_remark) {
		this.book_remark = book_remark;
	}
	public String getBook_img_name() {
		return book_img_name;
	}
	public void setBook_img_name(String book_img_name) {
		this.book_img_name = book_img_name;
	}
	public String getBook_img_url() {
		return book_img_url;
	}
	public void setBook_img_url(String book_img_url) {
		this.book_img_url = book_img_url;
	}
	public Integer getBook_status() {
		return book_status;
	}
	public void setBook_status(Integer book_status) {
		this.book_status = book_status;
	}

	public List<FileInfoBean> getFileInfoBeans() {
		return fileInfoBeans;
	}

	public void setFileInfoBeans(List<FileInfoBean> fileInfoBeans) {
		this.fileInfoBeans = fileInfoBeans;
	}
}
