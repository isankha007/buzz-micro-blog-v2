package com.sankha.twitter.tweet;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	private Boolean isRetweet;

	@ManyToMany
	@Column(name = "tweet-retweet-author-id")
	private Set<UserEntity> reTweetAuthors=new HashSet<>();

	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "tweet",cascade = CascadeType.ALL)
	//@JoinColumn(name="replies")
	private List<Reply> replies;


	@Column(nullable = false)
	private Boolean isDeleted=false;


//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "tweetEntity",cascade = CascadeType.ALL)
//	//@JoinColumn(name="likes")
//	private List<LikeEntity> likes;

	@ManyToMany
	@Column(name = "tweet-likedby-id")
	private Set<UserEntity> tweetLikedByUser=new HashSet<>();
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="media")
	private List<Media> medias;
	
	
	
	
	
}
