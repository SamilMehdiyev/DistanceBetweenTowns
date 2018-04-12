package com.java.exception;

import com.java.util.ErrorCode;

/**
 * Created by Shamil on 12-Apr-18.
 */
public class StartAndEndTownSamenessException extends RuntimeException {
    
    private static final long serialVersionUID = 7718828512143293558L;
    private final ErrorCode code;

    public StartAndEndTownSamenessException(ErrorCode code) {
        super(code.toString());
        this.code = code;
    }
    public StartAndEndTownSamenessException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }
    public StartAndEndTownSamenessException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
    public StartAndEndTownSamenessException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }
    public ErrorCode getCode() {
        return this.code;
    }
}
