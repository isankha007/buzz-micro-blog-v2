package com.sankha.twitter.follower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sankha.twitter.user.UserEntity;
import com.sankha.twitter.user.UserRepository;

@Service
public class FollowerService {
	@Autowired
	private FollowerRepository followRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	public void followUser(Authentication authentication,Long followingId) throws Exception {
		UserEntity loggedInUser= userRepository.findByUsername(authentication.getName());
		UserEntity followingUser= userRepository.findById(followingId).orElse(null);
		if(followingUser==null) {
			throw new Exception("User not found");
		}
		Follower followee=new Follower();
		followee.setFollower(loggedInUser);
		followee.setFollowing(followingUser);
		followRepo.save(followee);
	}
	
	public void unfollowUser(Authentication authentication,Long followingId) throws Exception {
		UserEntity loggedInUser= userRepository.findByUsername(authentication.getName());
		UserEntity followingUser= userRepository.findById(followingId).orElse(null);
		if(followingUser==null) {
			throw new Exception("User not found");
		}
		Follower followee=followRepo.findByFolloweeAndFollower(followingUser, loggedInUser).orElse(null);
		if(followee==null) {
			throw new Exception("User not following " + followingUser.getUsername());
		}
		followRepo.delete(followee);
	}
}
