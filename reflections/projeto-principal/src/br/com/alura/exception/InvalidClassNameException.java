package br.com.alura.exception;

public class InvalidClassNameException extends RuntimeException {

    public InvalidClassNameException() {
    }

    public InvalidClassNameException(String message) {
        super(message);
    }

    public InvalidClassNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidClassNameException(Throwable cause) {
        super(cause);
    }

    public InvalidClassNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
