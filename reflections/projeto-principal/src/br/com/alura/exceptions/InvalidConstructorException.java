package br.com.alura.exceptions;

public class InvalidConstructorException extends RuntimeException {

    public InvalidConstructorException() {
    }

    public InvalidConstructorException(String message) {
        super(message);
    }

    public InvalidConstructorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidConstructorException(Throwable cause) {
        super(cause);
    }

    public InvalidConstructorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
