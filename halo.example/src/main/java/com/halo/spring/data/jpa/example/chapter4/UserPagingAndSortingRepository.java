package com.halo.spring.data.jpa.example.chapter4;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 跟继承JpaSpecificationExecutor 类似，区别
 * 在JpaSpecificationExecutor中加入了查询条件Specification查询
 * 
 * 
 * 
 * @author zhaohuiliang
 *
 */
@Repository
public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, Long> {

}
