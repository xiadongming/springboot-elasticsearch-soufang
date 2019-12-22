package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.thymeleaf.util.StringUtils;

import com.example.demo.config.WebSecurityConfig;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;

/**
 * 根据数据库实现自定义认证策略
 * **/


public class AuthorProvider implements AuthenticationProvider{
	private final  Logger logger = LoggerFactory.getLogger(AuthorProvider.class);
	@Autowired
	private IUserService userService;
	//security中删除了md5，这里不做加密处理
	//private final Md4PasswordEncoder passwordEncoder = new Md4PasswordEncoder();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String inputPassWord = (String)authentication.getCredentials();
		
		User user = userService.findUserByName(userName);
		if(null == user) {
			throw new AuthenticationCredentialsNotFoundException("authError");
		}
		
		if(StringUtils.equals(inputPassWord, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorityList());
			
		}
		
		return (Authentication) new BadCredentialsException("authError");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
