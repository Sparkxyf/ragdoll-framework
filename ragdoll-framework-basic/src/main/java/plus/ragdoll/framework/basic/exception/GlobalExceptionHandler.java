package plus.ragdoll.framework.basic.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import plus.ragdoll.framework.basic.api.ApiResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

/***
 * @author  : spark
 * @date  : 2021/4/19 6:55 PM
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * DuplicateKeyException 处理器
     *
     * @param e the e
     * @return ResultResponse api response
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> duplicateKeyExceptionHandler(DuplicateKeyException e) {
        log.warn("DuplicateKeyException catch exception",e);
        return ApiResponse.error("业务主键有重复，请检查后重试");
    }


    /**
     * Hand runtime exception api response.
     *
     * @param e the e
     * @return the api response
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handRuntimeException(RuntimeException e) {
        log.error("RuntimeException catch exception", e);
        return ApiResponse.error(e.getMessage());
    }

    /**
     * Hand exception api response.
     *
     * @param e the e
     * @return the api response
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handException(Exception e) {
        log.error("GlobalExceptionHandler catch exception", e);
        return ApiResponse.error(e.getMessage());
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResultResponse api response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> parameterExceptionHandler(MethodArgumentNotValidException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return ApiResponse.error(fieldError.getDefaultMessage());
            }
        }
        return ApiResponse.error(e.getMessage());
    }

    /**
     * 自定义参数错误异常处理器
     *
     * @param e 自定义参数
     * @return ResultResponse api response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ParamErrorException.class})
    public ApiResponse<String> paramExceptionHandler(ParamErrorException e) {
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        return ApiResponse.error(e.getMessage());
    }


    /**
     * Service exception handler api response.
     *
     * @param exception the exception
     * @return the api response
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ServerErrorException.class})
    public ApiResponse<String> serviceExceptionHandler(ServerErrorException exception) {
        ApiResponse<String> response = ApiResponse.ok();
        response.setMsg(exception.getMessage());
        response.setSuccess(false);
        return response;
    }


    /**
     * Param exception handler api response.
     *
     * @param e the e
     * @return the api response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConnectException.class})
    public ApiResponse<String> paramExceptionHandler(ConnectException e) {
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        log.warn("{}",e);
        return ApiResponse.error("网络开小差了,请稍后重试");
    }



    /**
     * Param exception handler api response.
     *
     * @param e the e
     * @return the api response
     */
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({SocketTimeoutException.class})
    public ApiResponse<String> paramExceptionHandler(SocketTimeoutException e) {
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        log.warn("{}",e);
        return ApiResponse.error("网络开小差了,请稍后重试");
    }

}
