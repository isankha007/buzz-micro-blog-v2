package com.sankha.twitter.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.sankha.twitter.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class ReplyController {
	@Autowired
	ReplyService replyService;
	@Autowired
	ApiResponse apiResponse;
	
	@PostMapping(path = "/tweet/{tweet_id}/reply", produces = "application/json")
	public ResponseEntity<Object> userMakesCommentAtTweet(
			@RequestBody Reply reply, 
			Authentication authentication,
			@PathVariable("tweet_id") long tweet_id 
	) throws Exception{
		Reply savedComment = replyService.userMakesNewCommentAtTweet(authentication, tweet_id, reply);		
		apiResponse.setData(savedComment);
		apiResponse.setMessage("Comment crated");
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}


	@GetMapping(path = "/tweet/{tweet_id}/reply", produces = "application/json")
		  public ResponseEntity<Object> getUsersCommentAtTweet(
			@RequestBody Reply reply,
						Authentication authentication,
			@PathVariable("tweet_id") long tweet_id
				  ) throws Exception{
		   List<Reply> savedComments = replyService.showTweetComments(tweet_id);
		   System.out.println(savedComments);
		   apiResponse.setMessage("Comment crated");
		   apiResponse.setData(savedComments);
		   return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
		  }

	@DeleteMapping(path="/tweet/{reply_id}/reply", produces = "application/json")
	public ResponseEntity<Object> deleteComment(
			Authentication authentication,
			@PathVariable("reply_id")
			long reply_id
	) throws Exception
	{
		Reply deletedComment = replyService.deleteComment(authentication, reply_id);
		apiResponse.setData(deletedComment);
		apiResponse.setMessage("Comment deleted");
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}

}
