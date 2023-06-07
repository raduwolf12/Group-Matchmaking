package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * The Interface UserDetailsService.
 */
public interface UserDetailsService {
	
	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
