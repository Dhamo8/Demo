package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.entity.User;
import com.demo.repo.RoleRepository;
import com.demo.repo.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("User Name : "+username);
		//System.out.println(userDao.countByName(username));
		com.mitrahsoft.entity.User user = null;
		try {
			user = userDao.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}}catch(IncorrectResultSizeDataAccessException e) { 
			System.out.println("IncorrectResultSizeDataAccessException Caught");
			throw new UsernameNotFoundException("Duplicate User.");
		}catch(Exception e) {
			System.out.println("General Exception Caught : "+e.getMessage());
			throw new UsernameNotFoundException("Duplicate User.");
		}
		return new User(user.getName(),user.getPassword(),new ArrayList<>());
	}*/
	
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserDetailsImpl(user,roleRepository);
    }
	
	
	

}
 