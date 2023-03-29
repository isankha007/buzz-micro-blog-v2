package com.sankha.twitter.like;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sankha.twitter.tweet.Tweet;
import com.sankha.twitter.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LikeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeId;
	
	private Timestamp created;
	private Timestamp updated;
	
	@ManyToOne
	private UserEntity likeAuthor;
	//this entity not used
	@ManyToOne(targetEntity = Tweet.class)
	@JsonIgnore
	private Tweet tweetEntity;
}
