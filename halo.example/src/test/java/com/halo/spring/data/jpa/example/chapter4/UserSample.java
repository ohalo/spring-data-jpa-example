package com.halo.spring.data.jpa.example.chapter4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserSample {

    @Autowired
    UserRepository         repository;
    @Autowired
    AuditorAwareImpl       auditorAware;
    @Autowired
    AuditingEntityListener listener;

    @Before
    public void beforeSetUp() {
        User user = new User();
        user.setUsername("username");
        user.setFirstname("zhang");
        user.setLastname("san");
        repository.save(user);
        
        User user1 = new User();
        user1.setUsername("username");
        user1.setFirstname("zhang");
        user1.setLastname("san");
        repository.save(user1);
    }

    @Test
    public void testAuditEntity() throws Exception {

        assertThat(ReflectionTestUtils.getField(listener, "handler"), is(notNullValue()));

        User user = new User();
        user.setUsername("username");

        auditorAware.setUser(user);

        user = repository.save(user);
        
        
        User user1 = new User();
        user1.setUsername("username");
        
        user1 = repository.save(user1);

        assertThat(user.getCreatedBy(), is(user));
        assertThat(user1.getLastModifiedBy(), is(user));
    }

    @Test
    public void testNamedQuery() {
        User user = repository.findByTheUsersName("username");
        assertThat(user.getLastname(), is("san"));
    }

    @Test
    public void testMethodNameRuleQuery() {
        User user = repository.findByUsername("username");
        assertThat(user.getLastname(), is("san"));

    }

    @Test
    public void testDefineHqlQuery() {
        User user = repository.findUserByUsername("username");
        assertThat(user.getLastname(), is("san"));

    }

    @Test
    public void testDefineNativeSqlQuery() {
        User user = repository.findNativeUserByUsername("username");
        assertThat(user.getLastname(), is("san"));

    }

    @Test
    public void testDefineSqlPageQuery() {
        Pageable pageable = new PageRequest(0, 10);
        Page<User> userPage = repository.findUserByUsernameAndPage("username", pageable);
        assertThat(userPage.getTotalElements(), is(2L));

    }
    
    @Test
    public void testDefineNativeSqlPageQuery() {
        Pageable pageable = new PageRequest(0, 10);
        Page<User> userPage = repository.findNativeUserByUsernameAndPage("username", pageable);
        assertThat(userPage.getTotalElements(), is(2L));

    }

    @Test
    public void testSpecificationsQuery() {
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

}
