package com.uxuan.protocl.util;

public class DefineException extends RuntimeException {

	private static final long serialVersionUID = -3795471084779716949L;

    public DefineException() {
        super();
    }

    public DefineException(String message) {
    	super(message);
    }
    
    public DefineException(String format, Object...armgs) {
        super(String.format(format, armgs));
    }

    public DefineException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefineException(Throwable cause) {
        super(cause);
    }

}
