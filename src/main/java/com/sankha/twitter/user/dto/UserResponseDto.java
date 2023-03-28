package com.sankha.twitter.user.dto;

import java.util.List;


import com.sankha.twitter.follower.Follower;

import lombok.Data;

@Data
public class UserResponseDto {
	private Long userId;
	private String username;
	private String password;
	private String email;
	public boolean is_followed_by_user;
	
	private List<Follower> followers;
}
