package com.halo.spring.data.jpa.example.chapter4;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

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
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    /**
     * 自定义查询
     * 
     * @param id
     *            唯一标识
     * @return
     */
    T findOne(ID id);

    /**
     * 保存实体
     * 
     * @param entity
     *            实体
     * @return
     */
    T save(T entity);
}
