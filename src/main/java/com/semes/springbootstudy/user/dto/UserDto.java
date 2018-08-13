package com.semes.springbootstudy.user.dto;

import com.semes.springbootstudy.user.domain.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 관련 DTO 리스트
 * @author 안형진
 *
 */
public class UserDto {

	/**
	 * 회원가입 처리 시 이용되는 DTO 클래스
	 * @author 안형진
	 *
	 */
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SignUpDto {

		private String userId;
		private String password;
		private String name;
		private String email;

		@Builder
		public SignUpDto(String userId, String password, String name, String email) {
			this.userId = userId;
			this.password = password;
			this.name = name;
			this.email = email;
		}
		
		public User toEntity() {
			return User.builder()
					   .userId(this.userId)
					   .password(this.password)
					   .name(this.name)
					   .email(this.email)
					   .build();
		}

		@Override
		public String toString() {
			return "SignUpDto [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email
					+ "]";
		}

	}
	
	
	/**
	 * 회원정보 수정 시 이용되는 DTO 클래스
	 * @author 안형진
	 *
	 */
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class UpdateUserDto {
		
		private String name;
		private String email;

		@Builder
		public UpdateUserDto(String name, String email) {
			this.name = name;
			this.email = email;
		}

		@Override
		public String toString() {
			return "UpdateUserDto [name=" + name + ", email=" + email + "]";
		}
		
	}
	
}
