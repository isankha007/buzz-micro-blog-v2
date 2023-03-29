package com.sankha.twitter.follower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sankha.twitter.user.UserEntity;
import com.sankha.twitter.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowerService {
	@Autowired
	private FollowerRepository followRepo;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void followUser(Authentication authentication,Long followingId) throws Exception {
		UserEntity loggedInUser= userRepository.findByUsername(authentication.getName());
		if(loggedInUser.getUserId().longValue()==followingId) {
			throw new RuntimeException("Can not follow yourself");
		}
		UserEntity followingUser= userRepository.findById(followingId).orElse(null);
		if(followingUser==null) {
			throw new Exception("User not found");
		}
		/*if(followingUser.getFollowers().contains(loggedInUser)){
			throw new Exception("User Allready  followed");
		}*/
	/*	Follower followee=new Follower();
		followee.setFollower(loggedInUser);
		followee.setFollowing(followingUser);
		followRepo.save(followee);*/

		followRepo.follow(followingId,loggedInUser.getUserId());
	}

	@Transactional
	public void unfollowUser(Authentication authentication,Long followingId) throws Exception {
		UserEntity loggedInUser= userRepository.findByUsername(authentication.getName());
		UserEntity followingUser= userRepository.findById(followingId).orElse(null);
		if(followingUser==null) {
			throw new Exception("User not found");
		}/*
		Follower followee=followRepo.findByFolloweeAndFollower(followingUser, loggedInUser).orElse(null);
		if(followee==null) {
			throw new Exception("User not following " + followingUser.getUsername());
		}
		followRepo.delete(followee);*/

		followRepo.unfollow(followingId,loggedInUser.getUserId());
	}
}
