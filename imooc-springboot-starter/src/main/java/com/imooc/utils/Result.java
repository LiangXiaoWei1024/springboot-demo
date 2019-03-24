package com.imooc.utils;

/**
 * api接口响应
 */
public class Result {

    private int code;

    private String message;

    private Object result;

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static Result getSuccess(Object obj) {
        Result result = new Result();
        result.setCode(StatusCode.REQUEST_SUCCESS.getCode());
        result.setMessage(StatusCode.REQUEST_SUCCESS.getMessage());
        result.setResult(obj);
        return result;
    }

    public static Result getSuccess() {
        Result result = new Result();
        result.setCode(StatusCode.REQUEST_SUCCESS.getCode());
        result.setMessage(StatusCode.REQUEST_SUCCESS.getMessage());
        return result;
    }

    public static Result getError(StatusCode statusCode) {
        Result result = new Result();
        result.setCode(statusCode.getCode());
        result.setMessage(statusCode.getMessage());
        result.setResult(null);
        return result;
    }

    public static Result getError() {
        Result result = new Result();
        result.setCode(StatusCode.SYSTEM_ERROR.getCode());
        result.setMessage(StatusCode.SYSTEM_ERROR.getMessage());
        result.setResult(null);
        return result;
    }
}
