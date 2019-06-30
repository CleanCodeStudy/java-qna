package com.ccstudy.qna.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//EnableJpaAuditing 때문에 WebMvcTest안되서 이쪽으로 옮겼음.
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
