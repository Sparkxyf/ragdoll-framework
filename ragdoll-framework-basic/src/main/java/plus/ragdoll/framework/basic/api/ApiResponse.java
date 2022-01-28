package plus.ragdoll.framework.basic.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * @author spark
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private boolean success;
    private String msg;
    private String errorCode;


    public static <T> ApiResponse<T> ok() {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setData(null);
        return apiResponse;
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> error(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(false);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static ApiResponse<String> error(String msg) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(false);
        apiResponse.setMsg(msg);
        return apiResponse;
    }
}