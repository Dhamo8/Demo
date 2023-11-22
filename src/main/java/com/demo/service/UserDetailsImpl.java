package com.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.repo.RoleRepository;

@Service
public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7971442174376303667L;
	
	private User user ;
	
	 private RoleRepository roleRepository;

    public UserDetailsImpl(User user,RoleRepository roleRepository) {
    	this.user = user;
    	this.roleRepository = roleRepository;
    }
    
    public UserDetailsImpl() {
    }
    
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<Role> roles = null;
		if(user.getRoleIdList().size()>0) {
			roles = roleRepository.findAllById(user.getRoleIdList());
		}
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
