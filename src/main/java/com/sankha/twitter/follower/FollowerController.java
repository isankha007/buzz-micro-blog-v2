package com.sankha.twitter.follower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sankha.twitter.response.ApiResponse;

@RestController
@RequestMapping("/tweets")
public class FollowerController {
	@Autowired
    private FollowerService follService;
	@Autowired
	private ApiResponse apiResponse;
	
	@PostMapping(path = "/follower/user/{user_id}",produces = "applicatin/json")
	public ResponseEntity<Object> addFollower(Authentication authentication,
			@PathVariable("user_id") Long use_id) throws Exception {
		follService.followUser(authentication, use_id);
		apiResponse.setMessage("Follower added!");
	    apiResponse.setData(use_id);
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "unfollow/user/{user_id}", produces = "application/json")
	public ResponseEntity<Object> deleteFollowee(
			Authentication authentication, 
			@PathVariable("user_id") long user_id
	) throws Exception
	{
		follService.unfollowUser(authentication, user_id);
	    apiResponse.setMessage("Followee Deleted!");
	    apiResponse.setData(user_id);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
	}
	
}
