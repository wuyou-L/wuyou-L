package com.wuyou.bookStock.bean;
import java.sql.Timestamp;
import java.lang.String;
import java.lang.Integer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 库存
 * @author Autocode
 * 2022-02-26 23:21:47
 */
@ApiModel("库存")
public class BookStockBean {
	@ApiModelProperty(value="库存id")
	private Integer stock_id;

	@ApiModelProperty(value="图书id")
	private Integer stock_book_id;

	@ApiModelProperty(value="总库存")
	private Integer stock_num;

	@ApiModelProperty(value="可用库存")
	private Integer stock_occupable;

	@ApiModelProperty(value="批次")
	private String stock_batch;

	@ApiModelProperty(value="入库时操作账号")
	private String stock_create_account;

	@ApiModelProperty(value="入库时间")
	private Timestamp stock_create_time;

	@ApiModelProperty(value="来源")
	private String stock_source;


	public Integer getStock_id() {
		return stock_id;
	}
	public void setStock_id(Integer stock_id) {
		this.stock_id = stock_id;
	}
	public Integer getStock_book_id() {
		return stock_book_id;
	}
	public void setStock_book_id(Integer stock_book_id) {
		this.stock_book_id = stock_book_id;
	}
	public Integer getStock_num() {
		return stock_num;
	}
	public void setStock_num(Integer stock_num) {
		this.stock_num = stock_num;
	}
	public Integer getStock_occupable() {
		return stock_occupable;
	}
	public void setStock_occupable(Integer stock_occupable) {
		this.stock_occupable = stock_occupable;
	}
	public String getStock_batch() {
		return stock_batch;
	}
	public void setStock_batch(String stock_batch) {
		this.stock_batch = stock_batch;
	}
	public String getStock_create_account() {
		return stock_create_account;
	}
	public void setStock_create_account(String stock_create_account) {
		this.stock_create_account = stock_create_account;
	}
	public Timestamp getStock_create_time() {
		return stock_create_time;
	}
	public void setStock_create_time(Timestamp stock_create_time) {
		this.stock_create_time = stock_create_time;
	}
	public String getStock_source() {
		return stock_source;
	}
	public void setStock_source(String stock_source) {
		this.stock_source = stock_source;
	}

}
