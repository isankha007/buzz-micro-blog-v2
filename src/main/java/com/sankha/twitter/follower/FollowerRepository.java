package com.sankha.twitter.follower;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sankha.twitter.user.UserEntity;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
	@Query("select f from Follower f where f.follower = ?1")
	List<Follower> findTweetsThatUserFollows(UserEntity user);
	
	@Query("select f from Follower f where f.following = ?1 and f.follower=?2")
	Optional<Follower> findByFolloweeAndFollower(UserEntity followee, UserEntity follower);

	@Modifying
	@Query(value = "INSERT INTO user_subscriptions (user_id, subscriber_id) VALUES (?1, ?2)", nativeQuery = true)
	void follow(@Param("authUserId") Long authUserId, @Param("userId") Long userId);

	@Modifying
	@Query(value = "DELETE FROM user_subscriptions WHERE user_id = ?1 AND subscriber_id = ?2", nativeQuery = true)
	void unfollow(@Param("authUserId") Long authUserId, @Param("userId") Long userId);
}
