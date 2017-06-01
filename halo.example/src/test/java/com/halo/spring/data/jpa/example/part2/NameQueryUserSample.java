package com.halo.spring.data.jpa.example.part2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class NameQueryUserSample {

    @Autowired
    UserRepository repository;

    @Test
    public void auditEntityCreation() throws Exception {

        User user = new User();
        user.setUsername("username");
        user.setFirstname("zhang");
        user.setLastname("san");

        user = repository.save(user);
        user = repository.save(user);

        assertThat(user.getFirstname(), is("zhang"));
        assertThat(user.getLastname(), is("san"));
    }

}
