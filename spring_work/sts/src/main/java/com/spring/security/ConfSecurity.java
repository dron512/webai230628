package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
//				.password("{noop}admin").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/login/**").permitAll()
			.antMatchers("/resources/**").hasRole("ADMIN")
			.anyRequest().authenticated()
				.and()
			.formLogin().permitAll()
				.and()
			.logout().permitAll();
	}
}

/*
 * <security:http auto-config="true" use-expressions="true">
 * <security:intercept-url pattern="/sample/all" access="permitAll" /> <!--
 * /sample/member라는 url는 ROLE_MEMBER라는 권한이 있는 사용자만 접근 가능 -->
 * <security:intercept-url pattern="/sample/member"
 * access="hasRole('ROLE_MEMBER')" /> <security:intercept-url
 * pattern="/sample/admin" access="hasRole('ROLE_ADMIN')" />
 * 
 * <!-- login-page 속성의 URI는 반드시 GET방식으로 접근하는 URI를 지정 --> <security:form-login
 * login-page="/customLogin"/> <security:logout logout-url="/customLogout"
 * invalidate-session="true"/> <security:access-denied-handler
 * error-page="/accessError" /> </security:http>
 * 
 * 
 * <security:authentication-manager> <security:authentication-provider>
 * <security:user-service> <security:user name="member" password="member"
 * authorities="ROLE_MEMBER"/> <security:user name="admin" password="admin"
 * authorities="ROLE_MEMBER, ROLE_ADMIN"/> </security:user-service>
 * </security:authentication-provider> </security:authentication-manager>
 */
