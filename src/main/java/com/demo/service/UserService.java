package com.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.entity.User;
import com.demo.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public User createUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getById(Long id) {
		return userRepository.findById(id).get();
	}

	public User updateUser(User user, Long id) {
		User existedUser = userRepository.findById(id).get();
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		BeanUtils.copyProperties(user, existedUser);
		return userRepository.save(existedUser);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

}
