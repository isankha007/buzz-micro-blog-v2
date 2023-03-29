package com.sankha.twitter.follower;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sankha.twitter.user.UserEntity;

import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Follower {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	//@ManyToOne(targetEntity = UserEntity.class)
   // @JoinColumn(name = "follower")
	@ManyToOne
	@JoinColumn(name = "follower_user_id")
	private UserEntity follower;
	
	//@ManyToOne(targetEntity = UserEntity.class)
   // @JoinColumn(name = "following")
	@ManyToOne
	@JoinColumn(name = "following_user_id")
	private UserEntity following;
	
	

}
