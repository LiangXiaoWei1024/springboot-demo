package com.imooc.utils;

public enum StatusCode {

	SYSTEM_ERROR(9999,"系统错误"), 
	PARAM_ERROR(9900, "参数错误"),
	PARAM_MISSING(9909, "缺少指定参数"),
	PARAM_FORMAT_ERROR(9905, "参数格式不正确"),
	PARAM_SECRET_KEY_ERROR(9899, "参数格式不正确"),
	REQUEST_SUCCESS(1000, "请求处理成功"),
	REQUEST_FAIL(1001, "请求处理失败"),
	NO_RECORD(2000, "无记录"),
	VERIFICATION_CODE_ERROR(3000, "验证码错误"),
	ASSOCIATIONS_RECORD_EXIST(5000,"关联对象存在"),
	USER_EXIST(4000,"用户已存在"),
	USER_NOT_EXIST(4001,"用户不存在"),
	CODE_EXIST(4010,"产品已存在"),
	CODE_NOT_EXIST(4020,"产品不存在"),
	MAC_EXIST(4011,"MAC码已存在"),
	USER_PASSWORD_ERROR(4002,"用户名或密码错误"),
	USER_NOT_HAVE_PERMISSION(4003,"用户无权限"),
	ACCOUNT_ALREADY_LOGIN(4004,"该账号已经在其他设备上登录"),
	TWO_PW_DIF(4300,"两次密码输入不一致"),
	PW_ERRO(4400,"密码错误"),
	SIGN_ERROR(8000,"签名验证失败"),
	TOKEN_INVALID(5100,"accessToken失效"),
	NOT_LEADER(8001,"没有权限!");
	
	private int code;
	
	private String message;

	private StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
