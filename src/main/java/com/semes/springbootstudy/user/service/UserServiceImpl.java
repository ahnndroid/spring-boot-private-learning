package com.semes.springbootstudy.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.semes.springbootstudy.user.domain.User;
import com.semes.springbootstudy.user.dto.UserDto.SignUpDto;
import com.semes.springbootstudy.user.dto.UserDto.UpdateUserDto;
import com.semes.springbootstudy.user.repository.UserRepository;

/**
 * 사용자 관련 서비스 인터페이스 구현체 클래스
 * @author 안형진
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * 회원가입 처리 서비스 구현체
	 */
	@Override
	public User signUp(SignUpDto dto) {
		return userRepository.save(dto.toEntity());
	}

	/**
	 * 가입 사용자 리스트 조회 서비스 구현체
	 */
	@Override
	public List<User> getUsers(Pageable pageable) {
		Page<User> users = userRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
		return users.getContent();
	}

	/**
	 * 아이디에 해당하는 사용자 정보 조회 서비스 구현체
	 */
	@Override
	public User findById(Long id) {		
		return userRepository.findById(id).get();
	}

	/**
	 * 사용자 정보 갱신 서비스 구현체
	 */
	@Override
	public User updateUser(Long id, UpdateUserDto dto) {
		User user = this.findById(id);
		user.updateUserInfo(dto);
		userRepository.save(user);
		return user;
	}

}
