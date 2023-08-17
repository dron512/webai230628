package com.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 홈화면
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "homepage";
	}

	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "member/loginForm";
	}

	// 로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

}
