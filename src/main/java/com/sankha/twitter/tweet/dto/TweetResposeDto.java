package com.sankha.twitter.tweet.dto;

import java.sql.Timestamp;
import java.util.List;



import com.sankha.twitter.like.LikeEntity;
import com.sankha.twitter.reply.Reply;
import com.sankha.twitter.user.UserEntity;

import lombok.Data;

@Data
public class TweetResposeDto {
	private Long tweetId;
	private String text;
	private Timestamp created;
	private Timestamp updated;
	
	private UserEntity tweetAuthor;
	
	private List<Reply> replies;
	
	private List<LikeEntity> likes;
}
