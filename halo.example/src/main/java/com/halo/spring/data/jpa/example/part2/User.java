package com.halo.spring.data.jpa.example.part2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "t_user")
@NamedQuery(name = "User.findByTheUsersName", query = "from User u where u.username = ?1")
public class User extends AbstractPersistable<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = 5348845066813849633L;

    @Column(unique = true)
    private String            username;
    private String            firstname;
    private String            lastname;
}
