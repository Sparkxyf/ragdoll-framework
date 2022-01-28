package plus.ragdoll.framework.basic.exception;

/**
 * @author spark.xiong
 */
public class ServerErrorException extends RuntimeException {


    public ServerErrorException() {
    }

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(Throwable cause) {
        super(cause);
    }

    public ServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
