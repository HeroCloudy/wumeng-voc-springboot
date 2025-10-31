package com.wumeng.components.minio.exception;

public class WmFileException extends RuntimeException {

    public WmFileException() {
    }

    public WmFileException(String message) {
        super(message);
    }

    public WmFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public WmFileException(Throwable cause) {
        super(cause);
    }

    public WmFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
