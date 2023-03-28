package com.sankha.twitter.reply;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replyId;
	
	private String text;
	private Timestamp created;
	private Timestamp updated;
	
	@ManyToOne(targetEntity = UserEntity.class)
	private UserEntity replyAuthor;
	
	@ManyToOne(targetEntity = Tweet.class)
	@JsonIgnore
	private Tweet tweet;

}
