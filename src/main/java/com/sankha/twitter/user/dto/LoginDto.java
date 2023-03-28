package com.sankha.twitter.user.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LoginDto {
	private String username;
	private String jwt;
	private long userid;
}
