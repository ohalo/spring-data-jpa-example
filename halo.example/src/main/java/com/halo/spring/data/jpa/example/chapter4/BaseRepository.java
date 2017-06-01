package com.halo.spring.data.jpa.example.chapter4;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 定义一个持久层接口的基类，所有的持久层接口都继承这个类
 * 
 * 
 * @author zhaohuiliang
 *
 * @param <U>
 *            用户实体
 * @param <ID>
 *            唯一标识
 */
@NoRepositoryBean
public interface BaseRepository<U, ID extends Serializable> extends JpaRepository<U, ID> {

}
