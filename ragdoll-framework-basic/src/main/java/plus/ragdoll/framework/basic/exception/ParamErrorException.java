package plus.ragdoll.framework.basic.exception;

/**
 * @author : spark
 * @date : 2020-10-06 16:01
 */
public class ParamErrorException extends RuntimeException {
    public ParamErrorException() {
    }

    public ParamErrorException(String message) {
        super(message);
    }

    public ParamErrorException(Throwable cause) {
        super(cause);
    }

    public ParamErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}