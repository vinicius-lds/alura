package br.com.alura.exception;

public class InvalidFieldNameException extends RuntimeException {

    public InvalidFieldNameException() {
    }

    public InvalidFieldNameException(String message) {
        super(message);
    }

    public InvalidFieldNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFieldNameException(Throwable cause) {
        super(cause);
    }

    public InvalidFieldNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
