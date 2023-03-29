package com.sankha.twitter.tweet;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sankha.twitter.exception.TweetNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sankha.twitter.tweet.dto.CreateTweetDto;
import com.sankha.twitter.tweet.dto.TweetResposeDto;
import com.sankha.twitter.user.UserEntity;
import com.sankha.twitter.user.UserRepository;
import com.sankha.twitter.util.TimestampUtil;

@Service
public class TweetService {
	
	@Autowired
	private TweetRepository tweetRepo;
	@Autowired
	private UserRepository userRepo;
	
	  @Autowired
	  private ModelMapper modelMapper;
	  @Autowired
	  TimestampUtil timestampUtil;
	  
	  public List<TweetResposeDto> getFeed(UserEntity loggedInUser)
		{
			Tweet latestUserTweet = null;
			List<Tweet> userTweets = tweetRepo.findLatestTweetByUser(loggedInUser.getUserId());
			if(userTweets.size() > 0) latestUserTweet = userTweets.get(0);


			List<Tweet> followTweets = tweetRepo.findTweetsThatUserFollows(loggedInUser);
			if(latestUserTweet != null && latestUserTweet.getUpdated().after(timestampUtil.oneMinuteBackTimestamp()))
			{
				followTweets.add(0,latestUserTweet);
			}
			followTweets.addAll(showMyTweets(loggedInUser.getUserId()));

			List<TweetResposeDto> finalFeed = followTweets.stream().map(tweet -> {
				int retweetCount=tweet.getReTweetAuthors().size();
				TweetResposeDto map = modelMapper.map(tweet, TweetResposeDto.class);
				map.setRetweetCount(retweetCount);
				return 	map;
			}).collect(Collectors.toList());
			return finalFeed;
		}
	  
	  public Tweet createTweet(Authentication authentication, Tweet newTweet)
		{		
	        UserEntity LoggedInUser = userRepo.findByUsername(authentication.getName());
	        newTweet.setTweetAuthor(LoggedInUser);
	      
	        Timestamp currentTimestamp = timestampUtil.currentTimestamp();
	        newTweet.setCreated(currentTimestamp);
	        newTweet.setUpdated(currentTimestamp);
	       // newTweet.setTweet_created_at(currentTimestamp );
	       // newTweet.setTweet_updated_at(currentTimestamp );        
	        return tweetRepo.save(newTweet);
		}


	public TweetResposeDto reTweet(Authentication authentication, Long tweetId) throws Exception {
		UserEntity LoggedInUser = userRepo.findByUsername(authentication.getName());
		Tweet newTweet = getTweet(tweetId);
		if(newTweet.getReTweetAuthors().contains(LoggedInUser)){
			newTweet.getReTweetAuthors().remove(LoggedInUser);
		}else{
			newTweet.getReTweetAuthors().add(LoggedInUser);
		}

		return modelMapper.map(tweetRepo.save(newTweet),TweetResposeDto.class);
	}
	  
	public TweetResposeDto createTweet(CreateTweetDto tweet) {
		 var tweetTemp = modelMapper.map(tweet, Tweet.class);
		 var user= userRepo.findById(1L);
		 tweetTemp.setTweetAuthor(user.get());
	     // user.setPassword(passwordEncoder.encode(request.getPassword()));
	      var savedTweet = tweetRepo.save(tweetTemp);
	      var response = modelMapper.map(savedTweet, TweetResposeDto.class);
	      
	      return response;
	}
	
	
	
   public Tweet getTweet(Long id) throws Exception {
	   Tweet tweet= tweetRepo.findById(id).orElse(null);
	   if(tweet==null) {
		   throw new Exception("Tweet not found");
	   }
	   return tweet;
   }
   
   public List<Tweet> showMyTweets(long userId){
	   List<Tweet> tweets= tweetRepo.findLatestTweetByUser(userId);
	   return tweets;
   }

	public void deleteMyTweets(long userId,long tweetId){
		//Tweet tweet= tweetRepo.findById(tweetId).orElseThrow(()->new TweetNotFoundException("Tweet","Tweet id",tweetId));
		Tweet latestTweetByUserAndTweetId = tweetRepo.findLatestTweetByUserAndTweetId(userId, tweetId)
				.orElseThrow(()->new TweetNotFoundException("Tweet","Tweet id",tweetId));
		latestTweetByUserAndTweetId.setIsDeleted(true);
		tweetRepo.save(latestTweetByUserAndTweetId);

	}
	
 
}
