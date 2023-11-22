package com.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Role;
import com.demo.repo.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;


	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findById(Long id) {
		Role role = null;
		int i=9;
		try {
			role = roleRepository.findById(id).get();
		}
		catch(Exception e) {
		 role = new Role();	
		}
		return role;
	}

	public Role save(Role role) {
		return roleRepository.save(role);
	}

	public void deleteById(Long id) {
		roleRepository.deleteById(id);

	}

}
