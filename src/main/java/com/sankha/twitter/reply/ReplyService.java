package com.sankha.twitter.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sankha.twitter.tweet.Tweet;
import com.sankha.twitter.tweet.TweetRepository;
import com.sankha.twitter.user.UserEntity;
import com.sankha.twitter.user.UserRepository;
import com.sankha.twitter.util.TimestampUtil;

@Service
public class ReplyService {
	@Autowired
	private TweetRepository tweetRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ReplyRepository replyRepo;	
	@Autowired
	private TimestampUtil timestampUtil;
	
	public Reply userMakesNewCommentAtTweet(Authentication authentication, Long tweet_id, Reply reply ) throws Exception
	{
		Tweet CommentedTweet = tweetRepo.findById(tweet_id).orElse(null);
		if(CommentedTweet == null)
		{
			throw new Exception("Tweet not found!");
		}
        UserEntity LoggedInUser = userRepo.findByUsername(authentication.getName());
        reply.setTweet(CommentedTweet);
        reply.setReplyAuthor(LoggedInUser);
        reply.setCreated(timestampUtil.currentTimestamp());
        reply.setUpdated(timestampUtil.currentTimestamp());
        
		return replyRepo.save(reply);		
	}
	/*
	public Comment deleteComment(Authentication authentication, Long comment_id) throws Exception
	{
		Comment commentToDelete = R_Comment.findById(comment_id).orElse(null);
		if(commentToDelete == null)
		{
			throw new Exception("Comment not found");
		}
		User LoggedInUser = R_User.findByUsername(authentication.getName());

		if(commentToDelete.getComment_user_id() != LoggedInUser)
		{
			throw new Exception("Not authorized to delete this comment");
		}
		
		R_Comment.delete(commentToDelete);
				
		return commentToDelete;
	}*/
	public List<Reply> showTweetComments(long tweet_id)
	{
		Tweet commented_tweet = tweetRepo.findById(tweet_id).orElse(null);
		return replyRepo.findByTweetId(commented_tweet);
	}
}
