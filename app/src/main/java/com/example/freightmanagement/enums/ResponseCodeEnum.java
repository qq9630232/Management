package com.example.freightmanagement.enums;

/**
 * Created by Administrator on 2020/8/6.
 */

public enum ResponseCodeEnum {

    SUCCESS(0,"SUCCESS"),
    FAILED(404,"FAILED");
    private Integer code;

    private String msg;

    ResponseCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ResponseCodeEnum find(int timeType) {
        ResponseCodeEnum[] values = ResponseCodeEnum.values();
        for (ResponseCodeEnum value : values) {
            if (value.getCode().equals(timeType)){
                return value;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
