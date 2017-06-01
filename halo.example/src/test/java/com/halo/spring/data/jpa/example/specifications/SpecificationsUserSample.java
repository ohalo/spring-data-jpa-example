package com.halo.spring.data.jpa.example.specifications;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.halo.spring.data.jpa.example.example4.User;
import com.halo.spring.data.jpa.example.example4.UserRepository;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class SpecificationsUserSample {

    @Autowired
    UserRepository repository;

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
    public void testFindOne() {
        /**
         * 定义Specification 根据username查询
         */
        User user = repository.findOne(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("username").as(String.class), "username");
            }
        });
        assertThat(user.getLastname(), is("san"));
    }

    @Test
    public void testFindAll() {
        /**
         * 定义Specification 根据username查询
         */
        List<User> users = repository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("username").as(String.class), "%username%");
            }
        });
        assertThat(users.size(), is(3));
    }

    @Test
    public void testFindAllByPage() {

        Pageable pageable = new PageRequest(0, 10);
        /**
         * 定义Specification 根据username查询
         */
        Page<User> users = repository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get("username").as(String.class), "username"), cb.equal(root.get("firstname").as(String.class), "zhang"));
            }
        }, pageable);
        assertThat(users.getTotalElements(), is(1L));
        assertThat(users.getNumberOfElements(), is(1));
    }

    @Test
    public void testFindAllBySort() {

        Sort sort = new Sort(Direction.DESC, "id");
        /**
         * 定义Specification 根据username查询
         */
        List<User> users = repository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.like(root.get("username").as(String.class), "%username%"));
            }
        }, sort);
        assertThat(users.size(), is(3));
        assertThat(users.get(0).getId(), is(3L));
    }

    @Test
    public void testCount() {
        /**
         * 定义Specification 根据username查询
         */
        long count = repository.count(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.like(root.get("username").as(String.class), "%username%"));
            }
        });
        assertThat(count, is(3L));
    }

}
