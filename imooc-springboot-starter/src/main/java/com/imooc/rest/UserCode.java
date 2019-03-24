package com.imooc.rest;

public enum UserCode  implements ResultCode {
	USER_SUCCESS(true,200,"操作成功"),
	USER_FULL(false,401,"操作失败"),
	USER_CODE_FULL(false,402,"验证码错误，请重新输入！");
	
	
	
	//操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private  UserCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
