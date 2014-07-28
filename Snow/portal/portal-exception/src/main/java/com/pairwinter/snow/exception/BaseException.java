package com.pairwinter.snow.exception;

import com.pairwinter.snow.utils.json.JsonUtils;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by damon on 14-7-11.
 */
public class BaseException extends RuntimeException implements Serializable {
    public final static long	serialVersionUID	= 12345678;
    private boolean				debug				= true;
    private String				trace;
    private int					code				= 500;
    private String              type = BaseException.BUSINESS_EXCEPTION;
    public static final String BUSINESS_EXCEPTION = "Buiness";
    public static final String CONTROLLER_EXCEPTION = "Controller";
    private Object[] messageArgs;

    public BaseException() {
        super();
        init();
    }

    public BaseException(String message) {
        super(message);
        init();
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
        init();
    }
    public BaseException(int code, String message,  Object[] messageArgs) {
        super(message);
        this.code = code;
        this.messageArgs = messageArgs;
        init();
    }
    public BaseException(int code, String message, Throwable throwable, Object[] messageArgs) {
        super(message);
        this.code = code;
        this.messageArgs = messageArgs;
        init();
    }
    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
        init();
    }

    public BaseException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        init();
    }

    public BaseException(Throwable throwable) {
        super(throwable);
        init();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return (debug ? trace : "") + super.getMessage();
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public void setMessageArgs(Object[] messageArgs) {
        this.messageArgs = messageArgs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private void init() {
        StackTraceElement traces[] = getStackTrace();
        String className = traces[0].getClassName();
        int n = className.lastIndexOf('.');
        if(n > 0)
            className = className.substring(n + 1);
        trace = className + "." + traces[0].getMethodName() + "[line: " + traces[0].getLineNumber() + "]: ";
    }

    public String toJSON(){
        Map<String,Object> json = new HashMap<String, Object>();
        json.put("code",this.getCode());
        json.put("type",this.getType());
        return JsonUtils.toJson(json);
    }
}
