package com.ccstudy.qna.web.auth;

import com.ccstudy.qna.web.auth.manager.AccessUserManager;
import com.ccstudy.qna.web.auth.manager.AccessUserSessionManager;
import com.ccstudy.qna.web.auth.manager.AccessUserStubManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AccessUserManagerConfig {

    @Bean
    @ConditionalOnMissingBean
    public AccessUserManager accessUserManager() {
        return new AccessUserSessionManager();
    }

    @Profile({"test"})
    @Configuration
    class ManagerTestConfig {
        @Bean
        public AccessUserManager accessUserManager() {
            return new AccessUserStubManager();
        }
    }
}
