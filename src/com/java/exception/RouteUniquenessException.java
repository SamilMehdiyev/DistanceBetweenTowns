package com.java.exception;

import com.java.util.ErrorCode;

/**
 * Created by Shamil on 11-Apr-18.
 */
public class RouteUniquenessException extends Exception {
    
    private static final long serialVersionUID = 7718828512143293558L;
    private final ErrorCode code;
    
    public RouteUniquenessException(ErrorCode code) {
        super(code.toString());
        this.code = code;
    }
    public RouteUniquenessException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }
    public RouteUniquenessException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
    public RouteUniquenessException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }
    public ErrorCode getCode() {
        return this.code;
    }
}
