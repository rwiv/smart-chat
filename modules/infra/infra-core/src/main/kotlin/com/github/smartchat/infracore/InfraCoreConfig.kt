package com.github.smartchat.infracore

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableAutoConfiguration
@EnableJpaAuditing
class InfraCoreConfig {
}