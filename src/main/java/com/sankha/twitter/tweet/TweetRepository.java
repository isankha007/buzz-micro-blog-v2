package com.sankha.twitter.tweet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sankha.twitter.user.UserEntity;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
	@Query("select t from Tweet t where tweet_author_user_id =?1 and is_deleted=0 order by updated desc")
	List<Tweet> findLatestTweetByUser(Long userid);
	
	@Query( value="SELECT * "
			+ "FROM tweet "
			+ "WHERE is_deleted = 0 AND "
			+ "tweet_author_user_id IN "
			+ "("
			+ "SELECT following FROM follower WHERE follower = ?1"
			+ ") "
			+ "ORDER BY updated DESC",
			nativeQuery = true
	)
	List<Tweet> findTweetsThatUserFollows(UserEntity user);

	@Query("select t from Tweet t where tweet_author_user_id =?1 and tweet_id =?2 and is_deleted=0")
	Optional<Tweet> findLatestTweetByUserAndTweetId(long userId, long tweetId);
}
