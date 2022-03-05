package com.everybodydance.commons;

public enum ETEnums {
    SUCCESS(200,"success"),
    ERROR(444,"error"),
    LOGIN_SUCCESS(666,"login_success"),
    LOGIN_ERROR(445,"login_error"),
    REDIS_ERROR (446,"Redis_error"),
    CHECKCODE_ERROR(447,"checkCode_error"),
    LOGIN_REPEAT(449,"login_repeat");
    private int code;
    private String msg;
    private ETEnums(int code,String msg){
        this.code =code;
        this.msg =msg;
    }
    public String getMsg(){
        return msg;
    }
    public int getCode(){
        return code;
    }
}
