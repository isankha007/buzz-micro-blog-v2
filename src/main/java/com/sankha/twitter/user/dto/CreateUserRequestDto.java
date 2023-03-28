package com.sankha.twitter.user.dto;

import java.util.List;


import com.sankha.twitter.follower.Follower;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequestDto {
	private String username;
	private String password;
	private String email;
	private List<Follower> followers;
}
