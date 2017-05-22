package com.halo.spring.data.jpa.example.part1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User 的curd 操作持久化类
 * 
 * @author zhaohuiliang
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
