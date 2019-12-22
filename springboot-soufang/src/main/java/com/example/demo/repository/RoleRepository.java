package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	public List<Role> findRoleByUserId(Integer userId);

}
