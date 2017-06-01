package com.halo.spring.data.jpa.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.halo.spring.data.jpa.example.chapter4.AuditorAwareImpl;

/**
 * 注解配置
 * 
 * @SpringBootApplication 使用@SpringbootApplication注解
 *                        可以解决根类或者配置类头上注解过多的问题，一个@SpringbootApplication相当于@Configuration,@EnableAutoConfiguration和 @ComponentScan
 *                        并具有他们的默认属性值。
 * 
 * @EnableJpaAuditing 开启jpa
 * 
 * @author zhaohuiliang
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class UserConfiguration {

    @Bean
    AuditorAwareImpl auditorAware() {
        return new AuditorAwareImpl();
    }

}
