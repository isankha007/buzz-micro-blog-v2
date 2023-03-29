package com.sankha.twitter.follower.dto;

import com.sankha.twitter.user.UserEntity;
import com.sankha.twitter.user.dto.UserResponseDto;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class FollowerResponseDto {
    private Long Id;

    private String username;

//    private UserResponseDto follower;
//
//    private UserResponseDto following;
}
