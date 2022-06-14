package com.wuyou.base.resp;


import com.wuyou.base.resp.impl.ResultCode;

import java.util.HashMap;


public class Result {

	private Boolean success;
	private Integer code;
	private String message;
	private HashMap<String,Object> data = new HashMap<String,Object>();
	
	private Result() {}
	
	public static Result ok() {
		Result r = new Result();
		r.setSuccess(true);
		r.setCode(ResultCode.SUCCESS.getCode());
		r.setMessage(ResultCode.SUCCESS.getMessage());
		return r;
	}
	
	public static Result error() {
		Result r = new Result();
		r.setSuccess(false);
		r.setCode(ResultCode.ERROR_COMMON.getCode());
		r.setMessage(ResultCode.ERROR_COMMON.getMessage());
		return r;
	}
	
	
	public Result success(Boolean success) {
		this.setSuccess(success);
		return this;
	}
	
	public Result message(String message) {
		this.setMessage(message);
		return this;
	}
	
	
	public Result code(Integer code) {
		this.setCode(code);
		return this;
	}
	
	public Result data(String key,Object value) {
		this.data.put(key,value);
		return this;
	}
	
	public Result data(HashMap<String,Object> data) {
		this.setData(data);
		return this;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	
	
}
