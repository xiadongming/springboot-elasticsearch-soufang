package com.example.demo.test;

import java.util.Optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.SpringbootSoufangApplicationTests;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import junit.framework.Assert;

public class JunitTest extends SpringbootSoufangApplicationTests{
	
	 private static final Logger LOG = LoggerFactory.getLogger(JunitTest.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findOne() {
		 Optional<User> findById = userRepository.findById(1l);
		 User user = findById.get();
		 LOG.info(JSONObject.toJSONString(user));
	}
	
	

}
