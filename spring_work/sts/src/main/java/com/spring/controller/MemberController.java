package com.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.MemberDto;
import com.spring.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	@Qualifier("memberService")
	MemberService service;

	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(MemberDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 홈페이지로 이동
		return service.login(dto, request, response);
	}

	// 회원가입 페이지로 이동
	@RequestMapping(value = "joinPage")
	public String join() {
		return "member/joinForm";
	}

	// 회원가입
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(MemberDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 페이지로 이동
		return service.join(dto, request, response);
	}

	// 아이디 존재 여부 체크
	@PostMapping("checkId")
	public @ResponseBody String checkId(@RequestBody String loginId) {
		boolean exist = service.ExistId(loginId);
		if (exist) { // 사용가능한 아이디면
			return "success";
		} else {
			return "false";
		}
	}

	// @RequestBody : post 방식
	// @RequestParam : get 방식

	// 마이페이지로 이동
	@RequestMapping("myPage")
	public String myPage() {
		return "member/myPage";
	}

	// 마이페이지 수정
	@RequestMapping("update")
	public String update(MemberDto dto, HttpServletRequest request) {
		return service.update(dto, request);
	}

	// 회원 탈퇴
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam int idx, HttpSession session) {
		return service.delete(idx, session);
	}

	// 아이디 찾는 페이지로 이동
	@RequestMapping(value = "findId")
	public String findId() {
		return "member/findId";
	}

	// 아이디 찾기
	@RequestMapping(value = "findIdPro", method = RequestMethod.POST)
	public String findIdPro(MemberDto dto, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return service.findId(dto, request, response);
	}

	// 비밀번호 찾는 페이지로 이동
	@RequestMapping(value = "findPw")
	public String findPw() {
		return "member/findPw";
	}

	// 비밀번호 찾기
	@RequestMapping(value = "findPwPro", method = RequestMethod.POST)
	public String findPwPro(MemberDto dto, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return service.findPwPro(dto, request, response);
	}

	// 비밀번호 변경하는 페이지로 이동
	@RequestMapping(value = "updatePw", method = RequestMethod.POST)
	public String updatePw(MemberDto dto)
			throws IOException {
		return "member/updatePw";
	}

	// 비밀번호 변경
	@RequestMapping(value = "updatePwPro", method = RequestMethod.POST)
	public String updatePwPro(MemberDto dto, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		return service.updatePwPro(dto, request, response, session);
	}

}
