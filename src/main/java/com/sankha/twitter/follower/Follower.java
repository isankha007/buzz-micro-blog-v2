package com.sankha.twitter.follower;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.sankha.twitter.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follower {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "follower")
	private UserEntity follower;
	
	@ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "following")
	private UserEntity following;
	
	

}
