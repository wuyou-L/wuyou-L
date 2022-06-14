package com.wuyou.base.resp.impl;


import com.wuyou.base.resp.RespCodeService;

/**
 * 响应返回结果值
 *
 * @author cn.yaopeng
 * 2021.11.02
 */
public enum ResultCode implements RespCodeService {

    /* 成功 */
    SUCCESS(200, "成功"),
    SUCCESS_NO_DATA(200, "暂无数据"),
    SUCCESS_NO_APPROVE(200, "单据状态未变化"),

    // 5000 ~ 5999 表示错误
    ERROR_COMMON(5000, "失败"),    // 未定义错误返回码

//    ERROR_AUTHENTICATION(5001, "认证失败"),
    ERROR_TOKEN_INVALID(5002, "TOKEN 失效，请重新登录"),
    ERROR_USER_LOGIN(5003, "用户名或密码错误"),
    ERROR_INSERT(5004, "新增失败"),
    ERROR_UPDATE(5005, "修改失败"),
    ERROR_DELETE(5006, "删除失败"),
    ERROR_QUERY(5007, "未查到信息"),
    ERROR_INSERT_BATCH(5008, "批量新增失败"),
    ERROR_UPDATE_BATCH(5009, "批量更新失败"),
    ERROR_DELETE_BATCH(5010, "批量删除失败"),

    ERROR_NO_INOUT(5011, "没有需要出入库的数据"),
    ERROR_NO_BATCH(5012, "该批次不存在，不能进行出库"),
    ERROR_FORM_TYPE(5013, "单据类型错误"),
    ERROR_FORM_DID_OUTIN(5014, "单据已进行出入库操作"),
    ERROR_FORM_APPROVED(5015, "单据已审核"),
    ERROR_FORM_NO_APPROVE(5016, "单据未审核"),
    ERROR_DIVIDE(5017, "分货数量大于订购数量，或当前库存不足以分货"),
    ERROR_IN_DID(5018, "单据已入库"),
    ERROR_OUT_DID(5019, "单据已出库"),
    ERROR_STATUS_NO_CHANGE(5020, "单据状态未改变"),
    ERROR_DELIVER(5020, "发货数量大于订购数量，或当前库存不足以发货"),
    ERROR_NUM_OCCUPABLE(5021, "可用库存少于当前操作数量"),

    ERROR_AUTHORIZE_WH(5022, "没有操作该仓库的权限"),
    PARAM_NOT_VALID(5101, "参数无效"),
    PARAM_IS_BLANK(5102, "参数为空"),

    USER_NOT_LOGIN(5201, "用户未登录"),
    USER_ACCOUNT_EXPIRED(5202, "账号已过期"),
    USER_ACCOUNT_NOT_EXIST(5203, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(5204, "账号已存在"),

    NO_PERMISSION(5401, "没有权限"),
    NO_ROLE(5401, "权限不足"),
    ERROR_AUTHENTICATION(5415, "认证失败"),


    ;
    private Integer code;
    private String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
