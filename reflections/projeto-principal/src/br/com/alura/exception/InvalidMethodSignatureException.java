package br.com.alura.exception;

public class InvalidMethodSignatureException extends RuntimeException {

    public InvalidMethodSignatureException() {
    }

    public InvalidMethodSignatureException(String message) {
        super(message);
    }

    public InvalidMethodSignatureException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMethodSignatureException(Throwable cause) {
        super(cause);
    }

    public InvalidMethodSignatureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
