package com.halo.spring.data.jpa.example.part1;

import org.springframework.data.domain.AuditorAware;

/**
 * @CreatedBy和@LastModifiedBy 是需要实现AuditorAware接口来返回你需要插入的值。
 * 
 * @author zhaohuiliang
 *
 */
public class AuditorAwareImpl implements AuditorAware<User> {

    User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getCurrentAuditor() {
        return user;
    }

}
