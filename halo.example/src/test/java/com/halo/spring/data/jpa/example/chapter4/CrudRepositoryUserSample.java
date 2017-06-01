package com.halo.spring.data.jpa.example.chapter4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.halo.spring.data.jpa.example.chapter4.AuditorAwareImpl;
import com.halo.spring.data.jpa.example.chapter4.User;
import com.halo.spring.data.jpa.example.chapter4.UserRepository;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class CrudRepositoryUserSample {

    @Autowired
    UserRepository   repository;

    @Autowired
    AuditorAwareImpl auditorAware;

    @Before
    public void beforeSetUp() {
        User user = new User();
        user.setUsername("username");
        user.setFirstname("zhang");
        user.setLastname("san");
        repository.save(user);
    }

    @Test
    public void testSave() throws Exception {

        User user = new User();
        user.setUsername("username");
        user.setFirstname("zhang");
        user.setLastname("san");

        user = repository.save(user);

        assertThat(user.getFirstname(), is("zhang"));
        assertThat(user.getLastname(), is("san"));
    }

    @Test
    public void testSaveIter() {

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setUsername("username");
        user.setFirstname("zhang");
        user.setLastname("san");
        users.add(user);

        User user1 = new User();
        user1.setUsername("username1");
        user1.setFirstname("zhang1");
        user1.setLastname("san1");
        users.add(user1);

        User user2 = new User();
        user2.setUsername("username2");
        user2.setFirstname("zhang2");
        user2.setLastname("san2");
        users.add(user2);

        Iterable<User> userIter = repository.save(users);
        for (User user3 : userIter) {
            System.out.println(user3.toString());
        }
    }

    @Test
    public void testFindOne() {
        User user = repository.findOne(1L);
        assertThat(user.getFirstname(), is("zhang"));
    }

    @Test
    public void testExists() {
        System.out.println(repository.exists(1L));
    }

    @Test
    public void testFindAll() {
        Iterable<User> userIter = repository.findAll();
        for (User user3 : userIter) {
            System.out.println(user3.toString());
        }
    }

    @Test
    public void testFindAllByIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        Iterable<User> userIter = repository.findAll(ids);
        for (User user3 : userIter) {
            System.out.println(user3.toString());
        }
    }

    @Test
    public void testCount() {
        long count = repository.count();
        System.out.println(count);
    }

    @Test
    public void testDelete() {
        repository.delete(1L);

        // User user = repository.findOne(1L);
        // assertThat(user.getFirstname(), is("zhang"));
    }

    @Test
    public void testDeleteEntity() {

        User user = repository.findOne(1L);
        assertThat(user.getFirstname(), is("zhang"));

        repository.delete(user);
    }

    @Test
    public void testDeleteEntitys() {

        User user = repository.findOne(1L);
        assertThat(user.getFirstname(), is("zhang"));

        List<User> users = new ArrayList<>();
        users.add(user);

        repository.delete(users);
    }

    @Test
    public void testDeleteAll() {

        User user = repository.findOne(1L);
        assertThat(user.getFirstname(), is("zhang"));

        repository.deleteAll();

        Iterable<User> users = repository.findAll();
        for (User user2 : users) {
            System.out.println(user2.toString());
        }
    }

    @Test
    public void testCountLastname() {
        long count = repository.countByLastname("san");
        assertThat(count, is(1L));
    }

    @Test
    public void testDeleteLastname() {
        long id = repository.deleteByLastname("san");
        assertThat(id, is(1L));
    }

    @Test
    public void testRemoveLastname() {
        List<User> users = repository.removeByLastname("san");
        assertThat(users.size(), is(1));
    }

}
