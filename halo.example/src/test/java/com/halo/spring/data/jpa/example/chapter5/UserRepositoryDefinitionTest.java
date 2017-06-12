package com.halo.spring.data.jpa.example.chapter5;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.halo.spring.data.jpa.example.chapter4.User;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserRepositoryDefinitionTest {

    @Autowired
    UserRepositoryDefinition repository;

    @Before
    public void beforeSetUp() {
        User user = new User();
        user.setUsername("username");
        user.setFirstname("zhang");
        user.setLastname("san");
        repository.save(user);

        User user1 = new User();
        user1.setUsername("username1");
        user1.setFirstname("zhang1");
        user1.setLastname("san1");
        repository.save(user1);
    }

    @Test
    public void testFindByUsername() {
        User user = repository.findByUsername("username");
        assertThat(user.getLastname(), is("san"));

    }

}
