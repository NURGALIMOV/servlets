package tech.itpark.proggerhub.exception;

public class SecretPhrasePolicyViolationException extends RuntimeException {

    public SecretPhrasePolicyViolationException() {
        super();
    }

    public SecretPhrasePolicyViolationException(String message) {
        super(message);
    }

    public SecretPhrasePolicyViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecretPhrasePolicyViolationException(Throwable cause) {
        super(cause);
    }

    protected SecretPhrasePolicyViolationException(String message, Throwable cause, boolean enableSuppression,
                                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
