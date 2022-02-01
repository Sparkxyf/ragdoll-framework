package plus.ragdoll.framework.core.openfeign;

import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @author : spark
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "plus.ragdoll.openfeign.config", value = "enable")
public class OpenFeignAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ErrorDecoder errorDecoder() {
        return new OpenFeignErrorDecoder();
    }
}
