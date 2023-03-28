package com.sankha.twitter.follower;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sankha.twitter.user.UserEntity;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
	@Query("select f from Follower f where f.follower = ?1")
	List<Follower> findTweetsThatUserFollows(UserEntity user);
	
	@Query("select f from Follower f where f.following = ?1 and f.follower=?2")
	Optional<Follower> findByFolloweeAndFollower(UserEntity followee, UserEntity follower);
}
