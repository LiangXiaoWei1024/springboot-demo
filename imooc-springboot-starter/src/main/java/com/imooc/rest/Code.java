package com.imooc.rest;

public enum Code  implements ResultCode {
	USER_SOLR(false,-200,"登录已过有效期！请重新登录");
	
	
	
	//操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private  Code(boolean success, int code, String message){
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
