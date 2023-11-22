package com.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Role;
import com.demo.service.RoleService;

@RestController
@RequestMapping("/userRole")
public class RoleController {
	
	@Autowired
	RoleService roleService;

	@GetMapping
	public List<Role> getRoles() {
		return roleService.findAll();
	}

	@GetMapping("/{id}")
	public Role getRole(@PathVariable Long id) {
		return roleService.findById(id);
	}

	@PostMapping
	public Role createRole(@RequestBody Role role) throws URISyntaxException {
		return roleService.save(role);
	}

	@PutMapping("/{id}")
	public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
		Role currentRole = roleService.findById(id);
		BeanUtils.copyProperties(role, currentRole);
		return roleService.save(currentRole);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Long id) {
		roleService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
