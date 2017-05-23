package com.halo.spring.data.jpa.example.part1;

import org.springframework.data.domain.AuditorAware;

import lombok.Setter;

/**
 * @CreatedBy和@LastModifiedBy 是需要实现AuditorAware接口来返回你需要插入的值。
 * 
 * @author zhaohuiliang
 *
 */
public class AuditorAwareImpl implements AuditorAware<User> {

    @Setter User user;
    
    @Override
    public User getCurrentAuditor() {
        return user;
    }

}
