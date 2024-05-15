package com.maxim.taskmanagerapi.DataBaseLogic;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.maxim")
public class PersistancConfig {
}
