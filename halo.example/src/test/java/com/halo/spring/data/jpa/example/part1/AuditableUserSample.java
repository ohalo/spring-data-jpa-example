package com.halo.spring.data.jpa.example.part1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;



@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class AuditableUserSample {

    @Autowired
    UserRepository         repository;
    @Autowired
    AuditorAwareImpl       auditorAware;
    @Autowired
    AuditingEntityListener listener;

    @Test
    public void auditEntityCreation() throws Exception {

        assertThat(ReflectionTestUtils.getField(listener, "handler"), is(notNullValue()));

        User user = new User();
        user.setUsername("username");

        auditorAware.setUser(user);

        user = repository.save(user);
        user = repository.save(user);

        assertThat(user.getCreatedBy(), is(user));
        assertThat(user.getLastModifiedBy(), is(user));
    }

}
