package com.sankha.twitter.tweet;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.sankha.twitter.response.ApiResponse;
import com.sankha.twitter.tweet.dto.CreateTweetDto;
import com.sankha.twitter.tweet.dto.TweetResposeDto;
import com.sankha.twitter.user.UserEntity;
import com.sankha.twitter.user.UserRepository;
import com.sankha.twitter.user.UserService;

@RestController
@RequestMapping("/tweets")
public class TweetCotroller {
	@Autowired
    private TweetService tweetService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApiResponse apiResponse;
	
	@GetMapping(path = "/tweet/feed", produces = "application/json")
	public ResponseEntity<Object> getFeed(Authentication authentication)
	{			
        UserEntity LoggedInUser = userService.findByUsername(authentication.getName());
        List<TweetResposeDto> userFeed = tweetService.getFeed(LoggedInUser);
	    apiResponse.setMessage("User Feed!");
	    apiResponse.setData(userFeed);	    
	   
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
	
	@PostMapping(path = "/tweet/create", produces = "application/json")
	public ResponseEntity<Object> createTweet(Authentication authentication, @RequestBody Tweet newTweet)
	{
		TweetResposeDto savedTweet = tweetService.createTweet(authentication,newTweet);
	    apiResponse.setMessage("Tweet created!");
	    apiResponse.setData(savedTweet);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
	}

	@PostMapping(path = "/tweet/retweet/{tweetId}", produces = "application/json")
	public ResponseEntity<Object> reTweet(Authentication authentication, @PathVariable Long tweetId) throws Exception {
		TweetResposeDto savedTweet = tweetService.reTweet(authentication,tweetId);
		apiResponse.setMessage("Tweet Retweeted!");
		apiResponse.setData(savedTweet);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}

	@PostMapping(path = "/tweet/likes/{tweetId}", produces = "application/json")
	public ResponseEntity<Object> toggleLikes(Authentication authentication, @PathVariable Long tweetId) throws Exception {
		TweetResposeDto savedTweet = tweetService.toggleLike(authentication,tweetId);
		apiResponse.setMessage("Tweet Liked!");
		apiResponse.setData(savedTweet);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
	
	@GetMapping(path = "/tweet/user/{user_id}", produces = "application/json")
	public ResponseEntity<Object> showTweetsByUser(@PathVariable("user_id") long user_id)
	{			
		List<Tweet> userTweets = tweetService.showMyTweets(user_id);    
	    apiResponse.setMessage("Tweets by user");
	    apiResponse.setData(userTweets);
	    
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}


	@DeleteMapping(path = "/delete/{tweetId}", produces = "application/json")
	public ResponseEntity<Object> deleteTweet(Authentication authentication, @PathVariable Long tweetId)
	{

		UserEntity loggedInUser = userService.findByUsername(authentication.getName());
		tweetService.deleteMyTweets(loggedInUser.getUserId(),tweetId);
		apiResponse.setMessage("Tweet Deleted!");
		apiResponse.setData(tweetId);

		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.ACCEPTED);
	}
    
//    @PostMapping("")
//    public ResponseEntity<TweetResposeDto> createTweet(
//            @RequestBody CreateTweetDto request
//    ) {
//        var createdTweet = tweetService.createTweet(request);
//        return ResponseEntity.created(URI.create("/tweet/" + createdTweet.getTweetId())).body(createdTweet);
//    }
}
