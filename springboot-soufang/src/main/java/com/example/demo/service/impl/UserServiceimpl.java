package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.AuthorProvider;
import com.example.demo.service.IUserService;

@Service
public class UserServiceimpl implements IUserService{
	private final  Logger logger = LoggerFactory.getLogger(UserServiceimpl.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User findUserByName(String userName) {
		User user = userRepository.findByName(userName);
		if(user == null) {
			return null;
		}
		List<Role> roleList = roleRepository.findRoleByUserId(Integer.parseInt(String.valueOf(user.getId())));
		
		if(roleList ==null || roleList.isEmpty()) {
			throw new DisabledException("权限非法");
		}
		List<GrantedAuthority> authority = new ArrayList<>(); 
		for (Role role : roleList) {
			authority.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		}
		user.setAuthorityList(authority);
		
		return user;
	}

}
