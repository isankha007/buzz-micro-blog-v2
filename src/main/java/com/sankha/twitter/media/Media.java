package com.sankha.twitter.media;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sankha.twitter.tweet.Tweet;
import com.sankha.twitter.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mediaId;
	
	private String fileName;
	private String fileType;
	private Timestamp created;
	private Timestamp updated;
	private String url;

	
	@ManyToOne(targetEntity = Tweet.class)
	private Tweet tweet;

}
