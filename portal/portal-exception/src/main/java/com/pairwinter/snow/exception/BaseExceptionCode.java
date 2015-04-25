package com.pairwinter.snow.exception;

/**
 * Created by damon on 14-7-15.
 */
public enum BaseExceptionCode {

    GENERATE_PRIMARY_KEY_ERROR(9001,"The broadcastTemplate was used in threshold!"),
    BROADCASTTEMPLATE_DELETE_DEFAULT(9002,"Remove bc template failed."),
    CALENDAR_INVALID_EMAIL(17101,"Invalid email address(s)."),
    MULTIPLE_DISPLAY_LAYER_NAME(9003,"Multiple display layer name"),
    MULTIPLE_REGION_NAME(9004,"Multiple region name"),
    ARCGIS_MAP_URL_INVALID(9005,"ArcGIS map url is invalid!"),
    ENTITY_NOT_EXISTS(9006,"The Entity that you request is not exist.")

    ;
    //Course
    private int code;
    private String message;
    private BaseExceptionCode(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public String getStringCode(){
        return ""+code;
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
