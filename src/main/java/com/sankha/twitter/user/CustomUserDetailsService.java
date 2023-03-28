package com.sankha.twitter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user= userRepo.findByUsername(username);
		return new CustomUserDetails(user);
				
				/*org.springframework.security.core.userdetails.User//
		        .withUsername(username)//
		        .password(user.getPassword())//
		        .accountExpired(false)//
		        .accountLocked(false)//
		        .credentialsExpired(false)//
		        .disabled(false)//
		        .build();*/
	}
	
	public UserEntity getUserByUsername(String username)
	{
		return userRepo.findByUsername(username);
	}

}
