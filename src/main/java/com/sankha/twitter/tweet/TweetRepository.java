package com.sankha.twitter.tweet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sankha.twitter.user.UserEntity;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
	@Query("select t from Tweet t where tweet_author_user_id =?1 order by updated desc")
	List<Tweet> findLatestTweetByUser(Long userid);
	
	@Query( value="SELECT * "
			+ "FROM tweet "
			+ "WHERE tweet_author_user_id IN"
			+ "("
			+ "SELECT following FROM follower WHERE follower = ?1"
			+ ") "
			+ "ORDER BY updated DESC",
			nativeQuery = true
	)
	List<Tweet> findTweetsThatUserFollows(UserEntity user);
}
