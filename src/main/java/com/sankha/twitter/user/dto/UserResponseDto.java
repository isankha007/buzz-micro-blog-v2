package com.sankha.twitter.user.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.sankha.twitter.follower.Follower;

import com.sankha.twitter.follower.FollowerRepository;
import com.sankha.twitter.follower.dto.FollowerResponseDto;
import com.sankha.twitter.user.UserEntity;
import lombok.Data;

@Data
public class UserResponseDto {
	private Long userId;
	private String username;
//	private String password;
	//private String email;
	public boolean is_followed_by_user;

	private Set<Long> followersIds=new HashSet<>();

	private Set<Long> followingIds=new HashSet<>();
	//private Set<FollowerResponseDto> followers=new HashSet<>();

	//private Set<FollowerResponseDto> following=new HashSet<>();
}
