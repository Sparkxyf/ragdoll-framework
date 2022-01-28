package plus.ragdoll.framework.core.openfeign;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import plus.ragdoll.framework.basic.api.ApiResponse;
import plus.ragdoll.framework.basic.exception.ParamErrorException;

import java.nio.charset.StandardCharsets;

/***
 * @author : spark
 */
@Slf4j
public class OpenFeignErrorDecoder implements ErrorDecoder  {
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                log.debug("openfeign error response: {}", body);
                ApiResponse<Void> apiResponse = JSONUtil.toBean(body, new TypeReference<ApiResponse<Void>>() {},true);
                return new ParamErrorException(apiResponse.getMsg());
            } else {
                return new RuntimeException("No response body from openfeign api provider");
            }
        } catch (Exception e) {
            log.error("decode openfeign error response failed", e);
            return new RuntimeException(e);
        }
    }
}
