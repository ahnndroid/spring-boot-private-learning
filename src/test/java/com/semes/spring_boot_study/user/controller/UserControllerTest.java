package com.semes.spring_boot_study.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import com.semes.springbootstudy.SpringBootPrivateLearningApplication;
import com.semes.springbootstudy.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootPrivateLearningApplication.class, properties = "classpath:application.properties", webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
	
	private static final int USER_COUNT = 5;

	@Autowired
	private MockMvc mockMvc;
	
	private MockHttpServletRequestBuilder mockHttpRequest;
	
	private static List<User> testUsers;
	
	private static Long updateTargetId;
	
	
	@BeforeClass
	public static void setup() {
		testUsers = new ArrayList<User>();
		updateTargetId = -1L;
	}
	
	
	@Test
	public void test001_정상___회원가입_페이지_방문() throws Exception {
		
		System.out.println("====== [BEGIN] test001_정상___회원가입_페이지_방문 =======");
		
		this.mockHttpRequest = get("/users/signupForm");
		
		this.mockMvc.perform(this.mockHttpRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(xpath("//head/title").string("회원 가입"));
		
		System.out.println("====== [FINISHED] test001_정상___회원가입_페이지_방문 =======\n\n");
	}

	
	@Test
	@Repeat(value = USER_COUNT)
	public void test002_정상___회원가입_처리() throws Exception {
		
		System.out.println("====== [BEGIN] test002_정상___회원가입_처리 =======");
		
		String testMemberId = "test-" + LocalDateTime.now();
		
		this.mockHttpRequest = post("/users/signup")
									.param("userId", testMemberId)
									.param("password", testMemberId)
									.param("name", testMemberId)
									.param("email", testMemberId + "@foo.bar");
		
		this.mockMvc.perform(this.mockHttpRequest)
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/users"));
		
		System.out.println("====== [FINISHED] test002_정상___회원가입_처리 =======\n\n");
	}
	
	@Test
	public void test003_정상___가입_사용자_리스트_조회() throws Exception {
		
		System.out.println("====== [BEGIN] test003_정상___가입_사용자_리스트_조회 =======");
		
		this.mockHttpRequest = get("/users");
		
		Map<String, Object> map = this.mockMvc.perform(this.mockHttpRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("/user/list"))
			.andExpect(model().attributeExists("users"))
			.andReturn().getModelAndView().getModel();
		
		this.extractUsersFromModelMap(map);
		
		System.out.println("====== [FINISHED] test003_정상___가입_사용자_리스트_조회 =======\n\n");
	}
	
	@Test
	public void test004_정상___가입정보_수정_페이지_방문() throws Exception {
				
		System.out.println("====== [BEGIN] test004_정상___가입정보_수정_페이지_방문 =======");	
		
		this.mockHttpRequest = get("/users/" + this.getIdOfTargetTestUser() + "/form");
		
		this.mockMvc.perform(this.mockHttpRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(xpath("//head/title").string("개인 정보 수정"));
		
		System.out.println("====== [Finished] test004_정상___가입정보_수정_페이지_방문 =======\n\n");
	}
	
	@Test
	public void test005_정상___사용자_정보_수정() throws Exception {
	
		System.out.println("====== [BEGIN] test005_정상___사용자_정보_수정 ======");
		
		this.mockHttpRequest = put("/users/" + this.getIdOfTargetTestUser());
		
		this.mockMvc.perform(this.mockHttpRequest)
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/users"));
		
		System.out.println("====== [BEGIN] test005_정상___사용자_정보_수정 ======\n\n");
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////      [BEGIN] Private util methods      /////////////////////////	
	/**
	 * 테스트 시 이용될 사용자 추출을 위한 내부 유틸 메소드
	 * @param users
	 */
	private void extractUsersFromModelMap(Map<String, Object> modelMap) {
		
		@SuppressWarnings("unchecked")
		List<User> _testUsers = (List<User>) modelMap.get("users");
		
		UserControllerTest.testUsers = _testUsers;
		
	}
	
	/**
	 * 테스트 사용자 ID 리스트 중 임의의 ID 랜덤 추출을 위한 내부 유틸 메소드
	 * @return
	 */
	private Long getIdOfTargetTestUser() {
		
		if (UserControllerTest.updateTargetId != -1L) {
			return UserControllerTest.updateTargetId;
		}
		
		UserControllerTest.updateTargetId = UserControllerTest.testUsers.get(new Random().nextInt(Math.abs(UserControllerTest.testUsers.size() - 1))).getId();
		
		return UserControllerTest.updateTargetId;
	}
	/////////////////////////      [FINISHED] Private util methods      /////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////
}
