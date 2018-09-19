package com.semes.springbootstudy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.semes.springbootstudy.user.dto.UserDto;
import com.semes.springbootstudy.user.service.UserService;

/**
 * 사용자 관련 컨트롤러
 * @author 안형진
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * 회원가입 페이지 방문
	 * @return
	 */
	@GetMapping("/signupForm")
	public String signupForm() {
		return "/user/signup_form";
	}
	
	/**
	 * 회원가입 처리
	 * @param dto
	 * @return
	 */
	@PostMapping("/signup")
	public String signup(UserDto.SignUpDto dto) {
		userService.signUp(dto);
		return "redirect:/users";
	}
	
	/**
	 * 가입 사용자 리스트 조회
	 * @param model
	 * @return
	 */
	@GetMapping("")
	public String getUsers(@PageableDefault(page = 0, size = 20, sort = "id") Pageable pageable, Model model) {
		model.addAttribute("users", userService.getUsers(pageable));
		return "/user/list";
	}
	
	/**
	 * 가입 정보 수정 페이지 방문
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}/form")
	public String updateUserForm(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "/user/update_user_form";
	}
	
	/**
	 * 사용자 정보 수정
	 * @param id
	 * @param dto
	 * @return
	 */
	@PutMapping("/{id}")
	public String updateUser(@PathVariable Long id, UserDto.UpdateUserDto dto) {
		userService.updateUser(id, dto);
		return "redirect:/users";
	}
	
}
