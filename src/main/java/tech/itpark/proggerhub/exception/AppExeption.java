package tech.itpark.proggerhub.exception;

public class AppExeption extends RuntimeException {

    public AppExeption() {
        super();
    }

    public AppExeption(String message) {
        super(message);
    }

    public AppExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public AppExeption(Throwable cause) {
        super(cause);
    }

    protected AppExeption(String message, Throwable cause, boolean enableSuppression,
                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
