package com.semes.spring_boot_study.home.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import javax.xml.xpath.XPathExpressionException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.semes.springbootstudy.SpringBootPrivateLearningApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootPrivateLearningApplication.class, properties = "classpath:application.properties", webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomeController {

	@Autowired
	private MockMvc mockMvc;
	
	private MockHttpServletRequestBuilder mockHttpRequest;
	
	@Test
	public void test001_정상_홈페이지방문() throws XPathExpressionException, Exception {
		
		System.out.println("====== [BEGIN] test001_정상_홈페이지방문 =======");
		
		this.mockHttpRequest = get("/");
		
		this.mockMvc.perform(this.mockHttpRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(xpath("//head/title").string("Home"));
		
		System.out.println("====== [FINISHED] test001_정상_홈페이지방문 =======");
	}
}
