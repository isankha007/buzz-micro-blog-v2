package com.sankha.twitter.tweet;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sankha.twitter.like.LikeEntity;
import com.sankha.twitter.reply.Reply;
import com.sankha.twitter.user.UserEntity;
import com.sankha.twitter.media.Media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tweetId;
	
	private String text;
	private Timestamp created;
	private Timestamp updated;
	
	@ManyToOne
	private UserEntity tweetAuthor;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "tweet",cascade = CascadeType.ALL)
	//@JoinColumn(name="replies")
	private List<Reply> replies;


	@Column(nullable = false)
	private Boolean isDeleted;


	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="likes")
	private List<LikeEntity> likes;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="media")
	private List<Media> medias;
	
	
	
	
	
}
