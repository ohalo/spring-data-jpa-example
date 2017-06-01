package com.halo.spring.data.jpa.example.chapter4;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.ToString;

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
 * @Data lombok注解方式，默认实现类中的setter getter方法
 * 
 * @author zhaohuiliang
 *
 */
@Data
@Entity
@Table(name = "T_USER")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "User.findByTheUsersName", query = "from User u where u.username = ?1")
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
    private @CreatedDate Date               createdDate;

    /**
     * 最后更新时间
     * 
     * @LastModifiedDate 在实体更新的时候会设置值
     */
    private @LastModifiedDate Date          lastModifiedDate;

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

    private String                          firstname;
    private String                          lastname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
