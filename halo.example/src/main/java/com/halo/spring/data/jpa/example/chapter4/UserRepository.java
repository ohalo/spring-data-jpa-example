package com.halo.spring.data.jpa.example.chapter4;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User 的curd 操作持久化类 ,继承JpaSpecificationExecutor实现简单的查询操作
 * JpaSpecificationExecutor 提供了java中定义jpa查询的标准，根据criteria来组建查询条件
 * 
 * 
 * @author zhaohuiliang
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * NameQuery 形式的定义查询用户信息
     * 
     * @param username
     * @return
     */
    User findByTheUsersName(String username);

    /**
     * Spring data 根据方法名规则的定义查询用户信息
     * 
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 定义hql语句查询
     * 
     * @param username
     * @return
     */
    @Query("from User u where u.username = ?1")
    User findUserByUsername(String username);

    /**
     * 定义hql语句查询
     * 
     * @param username
     * @return
     */
    @Query(nativeQuery = true, value = "select * from T_USER u where u.username = ?1")
    User findNativeUserByUsername(String username);

    /**
     * 定义hql语句查询
     * 
     * @param username
     * @return
     */
    Page<User> findByUsername(String username, Pageable pageable);

    /**
     * 定义hql语句查询
     * 
     * @param username
     * @return
     */
    @Query(value = "from User u where u.username = ?1")
    Page<User> findUserByUsernameAndPage(String username, Pageable pageable);

    /**
     * 
     * 传入分页信息查询Slice
     * 
     * @param username
     * @param pageable
     * @return
     */
    @Query(value = "from User u where u.username = ?1")
    Slice<User> findByUsernameAndPageReturnSlice(String username, Pageable pageable);

    @Query(value = "from User u where u.username = ?1")
    List<User> findByUsernameAndSortReturnList(String username, Sort sort);

    @Query(value = "from User u where u.username = ?1")
    List<User> findByUsernameAndPageReturnLsit(String username, Pageable pageable);

    /**
     * 定义hql语句查询
     * 
     * @param username
     * @return
     */
    @Query(nativeQuery = true, 
            value = "select * from T_USER u where u.username = ?1 #{#pageable}",
            countQuery = "select count(*) from T_USER u where u.username = ?1")
    Page<User> findNativeUserByUsernameAndPage(String username, Pageable pageable);

    /**
     * 根据方法命名规则 查询count
     * 
     * @param lastname
     * @return
     */
    Long countByLastname(String lastname);

    /**
     * 根据lastname删除记录
     * 
     * @param lastname
     * @return
     */
    Long deleteByLastname(String lastname);

    /**
     * 根据lastname 删除记录
     * 
     * @param lastname
     * @return
     */
    List<User> removeByLastname(String lastname);
}
