package com.semes.springbootstudy.user.service;

import java.util.List;

import com.semes.springbootstudy.user.domain.User;
import com.semes.springbootstudy.user.dto.UserDto;

/**
 * 사용자 관련 서비스 인터페이스
 * @author 안형진
 *
 */
public interface UserService {

	/**
	 * 회원가입 처리 서비스
	 * @param dto
	 */
	public User signUp(UserDto.SignUpDto dto);
	
	/**
	 * 가입 사용자 리스트 조회 서비스
	 * @return
	 */
	public List<User> getUsers();
	
	/**
	 * 아이디에 해당하는 사용자 정보 조회 서비스
	 * @param id
	 * @return
	 */
	public User findById(Long id);
}
