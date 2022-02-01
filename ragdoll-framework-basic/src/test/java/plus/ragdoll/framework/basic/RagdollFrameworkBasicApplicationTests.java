package plus.ragdoll.framework.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.MessageSourceAccessor;
import plus.ragdoll.framework.basic.i18n.FrameworkMessageSource;

@Slf4j
@SpringBootTest
class RagdollFrameworkBasicApplicationTests {

    protected MessageSourceAccessor messages = FrameworkMessageSource.getAccessor();

    @Test
    void contextLoads() {
        String msg = messages.getMessage("NOT_FOUND");
        log.debug(msg);
    }

}
