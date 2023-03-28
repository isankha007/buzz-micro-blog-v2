package com.sankha.twitter.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sankha.twitter.tweet.Tweet;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Query(value="select * from"
			+ " reply"
			+ "where tweet_tweet_id in "
			+ "("
			+ "select tweet_id from tweet where"
			+ "tweet_id=?1"
			+ ")"
			+ "Order by created DESC",
			nativeQuery = true )
	List<Reply> findByTweetId(Tweet commented_tweet);

}
