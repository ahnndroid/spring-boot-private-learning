package com.semes.springbootstudy.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.semes.springbootstudy.user.dto.UserDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 관련 도메인 클래스
 * (실제적으로 데이터베이스와 연동되는 엔티티 클래스)
 * 
 * @author 안형진
 *
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String userId;

	private String password;
	private String name;
	private String email;
	
	@Builder
	public User(Long id, String userId, String password, String name, String email) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public void updateUserInfo(UserDto.UpdateUserDto dto) {
		this.name = dto.getName();
		this.email = dto.getEmail();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}

}
