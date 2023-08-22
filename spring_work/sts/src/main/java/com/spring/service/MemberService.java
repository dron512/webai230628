package com.spring.service;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.comm.LoginUtil;
import com.spring.comm.Message;
import com.spring.comm.Validate;
import com.spring.dao.MemberDao;
import com.spring.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService{
	@Autowired
	private MemberDao dao;

	@Autowired
	private Message message;

	@Autowired
	private Validate validate;

	@Autowired
	private LoginUtil loginUtil;

	// 아이디 존재 여부
	public boolean ExistId(String loginId) {
		System.out.println("일로오나");
		MemberDto res = dao.loginCheck(loginId);
		if (res == null) { // 사용가능한 아이디면 true
			return true;
		} else {
			return false;
		}
	}

	// 로그인
	public String login(MemberDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = dto.getMember_loginId();
		String pw = dto.getMember_password();

		// 유효성 검사
		if (validate.isEmpty(id) | validate.isEmpty(pw)) {
			message.alert(response, "alert('아이디와 비밀번호를 입력해주세요.');history.back();");
		}

		// 아이디가 존재하는지 확인
		MemberDto res = dao.loginCheck(id);

		if (res == null) { // 만약 존재하지 않는 아이디이면
			message.alert(response, "alert('존재하지 않는 아이디입니다.');history.back();"); // 이전 단계로 이동
		} else { // 아이디가 존재한다면 비밀번호를 비교
			if (!pw.equals(res.getMember_password())) { // 만약 비밀번호가 틀렸다면
				message.alert(response, "alert('아이디와 비밀번호를 확인해주세요.');history.back();");
			}
		}

		// 세션 생성 : 로그인후 그 상태를 유지하기 위해 생성
		HttpSession session = request.getSession();
		session.setAttribute("res", res);
		return "/member/loginResult";
	}

	// 회원 가입
	public String join(MemberDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 여부 확인
		if (loginUtil.isLogin(request)) {
			message.alert(response, "alert('로그인이 되어있습니다.');");
			return "redirect:/";
		}

		// 유효성 검사와 빈 값 검사
		String logindId = dto.getMember_loginId();
		String password = dto.getMember_password();
		String passwordCheck = request.getParameter("member_password_check");
		String name = dto.getMember_name();
		String tel = dto.getMember_tel();
		String email = dto.getMember_email();
		LocalDate joinDate = dto.getMember_joinDate();
		String category = dto.getMember_category();

		if (!validate.isValidated(logindId, "^[a-z0-9]{5,19}$")
				| !validate.isValidated(password, "^[A-Za-z0-9!@#$]{8,16}$")
				| !validate.isValidated(name, "^[가-힣]{2,10}$") | !validate.isValidated(tel, "^[0-9]{10,12}$")
				| !validate.isValidated(email, "^.{1,50}$")) {
			message.alert(response, "alert('로그인이 되어있습니다.');history.back();");
		}

		if (!password.equals(passwordCheck)) {
			message.alert(response, "alert('로그인이 되어있습니다.');history.back();");
		}

		// 패스워드 암호화

		// 회원가입
		dao.join(dto);

		return "redirect:/login";
	}

	// 마이페이지 수정
	public String update(MemberDto dto, HttpServletRequest request) {

		// 회원 수정
		dao.update(dto);

		HttpSession session = request.getSession();

		MemberDto res = dao.loginCheck(dto.getMember_loginId());
		session.setAttribute("res", res);

		return "redirect:/member/myPage";
	}

	// 회원 탈퇴
	public String delete(int idx, HttpSession session) {
		dao.delete(idx);
		session.invalidate();
		return "redirect:/";
	}

	// 아이디 찾기
	public String findId(MemberDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = dto.getMember_name();
		String email = dto.getMember_email();

		// 유효성 검사
		if (validate.isEmpty(name) | validate.isEmpty(email)) {
			message.alert(response, "alert('성명과 이메일을 입력해주세요.');history.back();");
		}

		String loginId = dao.findId(dto);

		HttpSession session = request.getSession();
		session.setAttribute("loginId", loginId);

		return "/member/findIdResult";
	}

	// 비밀번호 찾기
	public String findPwPro(MemberDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = dto.getMember_loginId();
		String email = dto.getMember_email();

		// 유효성 검사
		if (validate.isEmpty(id) | validate.isEmpty(email)) {
			message.alert(response, "alert('아이디와 이메일을 입력해주세요.');history.back();");
		}

		HttpSession session = request.getSession();
		
		// 회원 정보 불러오기
		MemberDto res = dao.userCheck(dto);
		
		// 존재하지 않는 아이디일 경우
		if (res == null) {
			message.alert(response, "alert('존재하지 않는 아이디입니다.');history.back();");
		}
		
		session.setAttribute("res", res);

		return "/member/updatePw";
	}

	// 비밀번호 변경
	public String updatePwPro(MemberDto dto, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		String pw = request.getParameter("member_password");
		String pwcheck = request.getParameter("member_passwordCheck");

		// 유효성 검사
		if (validate.isEmpty(pw) | validate.isEmpty(pwcheck)) {
			message.alert(response, "alert('비밀번호를 입력해주세요.');history.back();");
		}
		
		// 일치하는지 확인
		if(!pw.equals(pwcheck)) {
			message.alert(response, "alert('비밀번호와 일치하지 않습니다.');history.back();");
		}
		
		dto = (MemberDto)session.getAttribute("res");
		
		dto.setMember_password(pw);
		
		// 비밀번호 변경
		dao.updatePw(dto);
		
		session.invalidate();

		return "redirect:/login";
	}
	
	@Autowired
	@Qualifier("bcryptPasswordEncoder")
	PasswordEncoder bcryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		String id = dto.getMember_loginId();
//		String pw = dto.getMember_password();

		// 유효성 검사
//		if (validate.isEmpty(id) | validate.isEmpty(pw)) {
//			message.alert(response, "alert('아이디와 비밀번호를 입력해주세요.');history.back();");
//		}
//
//		// 아이디가 존재하는지 확인
//		MemberDto res = dao.loginCheck(id);
//
//		if (res == null) { // 만약 존재하지 않는 아이디이면
//			message.alert(response, "alert('존재하지 않는 아이디입니다.');history.back();"); // 이전 단계로 이동
//		} else { // 아이디가 존재한다면 비밀번호를 비교
//			if (!pw.equals(res.getMember_password())) { // 만약 비밀번호가 틀렸다면
//				message.alert(response, "alert('아이디와 비밀번호를 확인해주세요.');history.back();");
//			}
//		}

		System.out.println(dao);
		System.out.println(bcryptPasswordEncoder);

		MemberDto res = dao.loginCheck(username);
//
//		log.info(res.toString());
		
		return User.builder()
                .username("1234")
                .password(bcryptPasswordEncoder.encode("1234"))
                .roles("MEMBER")
                .build();
	}

}
