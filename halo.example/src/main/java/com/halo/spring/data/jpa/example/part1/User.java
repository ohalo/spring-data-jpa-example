package com.halo.spring.data.jpa.example.part1;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * 
 * @Entity 定义一个orm 实体
 * @Table 定义一个实体映射的表
 * @EntityListeners 当类中的字段被注解@LastModifiedDate 和@CreatedDate
 *                  ，需要加入AuditingEntityListener.class
 * 
 *                  不采用 @LastModifiedDate 和@CreatedDate 的方式：
 *                  可以实现一个UserEntityListener
 * 
 * @CreatedBy和@LastModifiedBy 是需要实现AuditorAware接口来返回你需要插入的值。
 * 
 * @author zhaohuiliang
 *
 */
@Data
@Entity
@Table(name = "T_USER")
@EntityListeners(AuditingEntityListener.class)
public class User {

    /**
     * @Id 定义主键
     * @GeneratedValue 定义主键自增长策略
     */
    private @Id @GeneratedValue Long        id;

    /**
     * 用户名称
     * 
     * 默认字段， 也可以写成 @Column(name = "username") 如果是驼峰命名法userName ，
     * 默认映射成数据库中的字段user_name
     */
    // @Column(columnDefinition = "", name = "username")
    private String                          username;

    /**
     * 创建时间
     * 
     * @CreatedDate 在实体添加的时候会设置值
     */
    private @CreatedDate LocalDateTime      createdDate;

    /**
     * 最后更新时间
     * 
     * @LastModifiedDate 在实体更新的时候会设置值
     */
    private @LastModifiedDate LocalDateTime lastModifiedDate;

    /**
     * 创建人
     * 
     * @CreatedBy 创建人，在实体添加的时候会设置值
     * @ManyToOne 多对一的关系，表示一个创建者 会创建多条用户记录
     */
    private @ManyToOne @CreatedBy User      createdBy;

    /**
     * 更新人
     * 
     * @LastModifiedBy 在实体更新的时候会设置值
     * @ManyToOne 多对一的关系，表示一个更新者 会更新多条用户记录
     */
    private @ManyToOne @LastModifiedBy User lastModifiedBy;

}
