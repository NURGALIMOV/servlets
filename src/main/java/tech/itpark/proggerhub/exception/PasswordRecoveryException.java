package tech.itpark.proggerhub.exception;

public class PasswordRecoveryException extends RuntimeException {

    public PasswordRecoveryException() {
        super();
    }

    public PasswordRecoveryException(String message) {
        super(message);
    }

    public PasswordRecoveryException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordRecoveryException(Throwable cause) {
        super(cause);
    }

    protected PasswordRecoveryException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
