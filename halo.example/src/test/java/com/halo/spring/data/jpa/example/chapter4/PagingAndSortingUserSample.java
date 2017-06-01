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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.halo.spring.data.jpa.example.chapter4.User;
import com.halo.spring.data.jpa.example.chapter4.UserPagingAndSortingRepository;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class PagingAndSortingUserSample {

    @Autowired
    UserPagingAndSortingRepository repository;

    @Before
    public void beforeSetUp() {
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

        repository.save(users);
    }

    @Test
    public void testFindAllByPageable() {
        Pageable pageable = new PageRequest(0, 10);
        Page<User> userPage = repository.findAll(pageable);
        assertThat(userPage.getContent().size(), is(3));
    }

    @Test
    public void testFindAllBySort() {
        Sort sort = new Sort(Direction.DESC, "id");
        Iterable<User> users = repository.findAll(sort);
        assertThat(users.iterator().next().getUsername(), is("username2"));
    }

}
