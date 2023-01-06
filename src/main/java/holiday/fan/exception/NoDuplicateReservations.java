package holiday.fan.exception;

public class NoDuplicateReservations extends RuntimeException {

    public NoDuplicateReservations() {
    }

    public NoDuplicateReservations(String message) {
        super(message);
    }

    public NoDuplicateReservations(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDuplicateReservations(Throwable cause) {
        super(cause);
    }

    public NoDuplicateReservations(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
