package com.semes.springbootstudy.user.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.semes.springbootstudy.user.domain.User;

/**
 * 사용자 관련 JPA 인터페이스 (일종의 DAO)
 * @author 안형진
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
