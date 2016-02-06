package Carters;

/**
 * Created by zenbox on 2/2/2016.
 */
public class RefreshException extends Exception {
    private static final long serialVersionUID = 1997753363232807009L;

    public RefreshException(){
    }

    public RefreshException(String message){
        super(message);
    }

    public RefreshException(Throwable cause){
        super(cause);
    }

    public RefreshException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
