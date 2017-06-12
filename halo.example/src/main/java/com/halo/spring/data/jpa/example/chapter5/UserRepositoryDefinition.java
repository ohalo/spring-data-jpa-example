package com.halo.spring.data.jpa.example.chapter5;

import org.springframework.data.repository.RepositoryDefinition;

import com.halo.spring.data.jpa.example.chapter4.User;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepositoryDefinition {

    /**
     * Spring data 根据方法名规则的定义查询用户信息
     * 
     * @param username
     * @return
     */
    User findByUsername(String username);

    void save(User user);
}
