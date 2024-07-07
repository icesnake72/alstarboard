package com.example.alstarboard.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.alstarboard.repository")
public class MyBatisConfiguration {
}
