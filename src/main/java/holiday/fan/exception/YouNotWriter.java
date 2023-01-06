package holiday.fan.exception;

public class YouNotWriter extends RuntimeException {

    public YouNotWriter() {
    }

    public YouNotWriter(String message) {
        super(message);
    }

    public YouNotWriter(String message, Throwable cause) {
        super(message, cause);
    }

    public YouNotWriter(Throwable cause) {
        super(cause);
    }

    public YouNotWriter(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
