package com.sankha.twitter.user;


import java.util.*;
import java.util.regex.Pattern;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern.Flag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sankha.twitter.follower.Follower;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String username;
	private String password;
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Flag.CASE_INSENSITIVE)	
	private String email;

	@ManyToMany
	@JoinTable(name = "user_subscriptions",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "subscriber_id"))
	private List<UserEntity> followers=new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "user_subscriptions",
			joinColumns = @JoinColumn(name = "subscriber_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserEntity> following=new ArrayList<>();
	
//    @OneToMany(mappedBy = "follower")
//	@JsonIgnore //temopary
//	private Set<Follower> followers=new HashSet<>();

//	@OneToMany(mappedBy="following")
//	private Set<Follower> following=new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserEntity that)) return false;
		return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(followers, that.followers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, username, password, email, followers);
	}
}