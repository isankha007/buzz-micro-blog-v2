package com.sankha.twitter.tweet.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.sankha.twitter.like.LikeEntity;
import com.sankha.twitter.reply.Reply;
import com.sankha.twitter.reply.dto.ReplyRespnseDto;
import com.sankha.twitter.user.UserEntity;

import com.sankha.twitter.user.dto.UserResponseDto;
import lombok.Data;

@Data
public class TweetResposeDto {
	private Long tweetId;
	private String text;
	private Timestamp created;
	private Timestamp updated;
	
	private UserResponseDto tweetAuthor;

	private long retweetCount;

	private Set<Long> reTweetAuthorsIds=new HashSet<>();
	
	private List<ReplyRespnseDto> replies;


	private long likeCount;

	private Set<UserEntity> tweetLikedByUser=new HashSet<>();
}
