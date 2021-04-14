package tech.itpark.proggerhub.exception;

public class SecretPhraseNotMatchedException extends RuntimeException {

    public SecretPhraseNotMatchedException() {
        super();
    }

    public SecretPhraseNotMatchedException(String message) {
        super(message);
    }

    public SecretPhraseNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecretPhraseNotMatchedException(Throwable cause) {
        super(cause);
    }

    protected SecretPhraseNotMatchedException(String message, Throwable cause, boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
