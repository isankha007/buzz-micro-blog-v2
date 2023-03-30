package com.sankha.twitter.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sankha.twitter.follower.Follower;
import com.sankha.twitter.follower.FollowerRepository;
import com.sankha.twitter.user.dto.CreateUserRequestDto;
import com.sankha.twitter.user.dto.TweetersList;
import com.sankha.twitter.user.dto.UserResponseDto;

@Service
public class UserService {
  @Autowired	
  private UserRepository repository;
  @Autowired
  private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private FollowerRepository followerRepo;
  
	public UserEntity createNewUser(CreateUserRequestDto request ) throws Exception
	{
		 var user = modelMapper.map(request, UserEntity.class);

		if(user_exists(user)) throw new Exception("User already exists!");
		
		String UserEmail = user.getEmail().trim();
		String UserUsername = user.getUsername().trim();
		String UserPassword = user.getPassword().trim();
		
		boolean UsernameIsNotValid = (UserUsername == null) || !UserUsername.matches("[A-Za-z0-9_]+");
		/* Email Restriction
		 * ---------------------
		 *This expression matches email addresses, and checks that they are of the proper form. 
		 *It checks to ensure the top level domain is between 2 and 4 characters long, 
		 *but does not check the specific domain against a list (especially since 
		 *there are so many of them now).		  		
		 */
		boolean EmailIsNotValid = (UserEmail == null ) || !UserEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		
		/* Password Restriction
		 * ------------------------
		 * At least 8 chars
		 * Contains at least one digit
		 * Contains at least one lower alpha char and one upper alpha char
		 * Contains at least one char within a set of special chars (@#%$^ etc.)
		 * Does not contain space, tab, etc.
		 */
		boolean PasswordIsNotValid = (UserPassword == null ) 
				|| !UserPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");
		
		if(UsernameIsNotValid) throw new Exception("Username not set or not valid!");
		if(PasswordIsNotValid ) throw new Exception("Password not set or not valid");
		if(EmailIsNotValid) throw new Exception("Email not set or not valid!");		
				
		
	    user.setPassword(passwordEncoder.encode(user.getPassword()));	    
	    return userRepo.save(user);
	}
	
	private Boolean user_exists(UserEntity user)
	{
		if(userRepo.findByUsername(user.getUsername()) != null) return true;
		if(userRepo.findByEmail(user.getEmail()) != null) return true;
		return false;
	}
	public TweetersList listUsers(Authentication authentication)
	{
		UserEntity LoggedInUser = userRepo.findByUsername(authentication.getName());
		List<UserEntity> userList = userRepo.findByUsernameNot(LoggedInUser.getUsername());
		
		TweetersList tweetersList = new TweetersList();
		for(UserEntity user: userList)
		{
			UserResponseDto tweeter = new UserResponseDto();
			tweeter.setUserId(user.getUserId());
			//tweeter.user_id = user.getUser_id();
			tweeter.setUsername(user.getUsername());
			//tweeter.username = user.getUsername();
			//tweeter.email = user.getEmail();
			//tweeter.setEmail(user.getEmail());
			Follower f = followerRepo.findByFolloweeAndFollower(user,LoggedInUser).orElse(null);
			if(f == null)
			{
				tweeter.is_followed_by_user = false;
			}else {
				tweeter.is_followed_by_user = true;
			}
			tweetersList.tweetersList.add(tweeter);			
		}
		
		return tweetersList;
	}

	public UserEntity findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public UserResponseDto getUserById(Authentication authentication,Long userId) {
		UserEntity LoggedInUser = userRepo.findByUsername(authentication.getName());
//		if(LoggedInUser.getUserId().longValue()==userId){
//			return modelMapper.map(LoggedInUser,UserResponseDto.class);
//		}
		UserEntity userFound = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
		UserResponseDto user = modelMapper.map(userFound, UserResponseDto.class);
		user.getFollowersIds().addAll(userFound.getFollowers().stream().map(f->f.getUserId()).collect(Collectors.toSet()));
		user.getFollowingIds().addAll(userFound.getFollowing().stream().map(f->f.getUserId()).collect(Collectors.toSet()));

		return user;//modelMapper.map(userRepo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found")),UserResponseDto.class);
	}
	
  /*public UserResponseDto createUser(CreateUserRequestDto request) {
	  var user = modelMapper.map(request, UserEntity.class);
     // user.setPassword(passwordEncoder.encode(request.getPassword()));
      var savedUser = repository.save(user);
      var response = modelMapper.map(savedUser, UserResponseDto.class);
      // OPTION 1: Server side token
      // var token = authTokenService.createToken(savedUser);
      // response.setToken(token);
      // OPTION 2: JWT
     // var token = jwtService.createJwt(savedUser.getUsername());
      //response.setToken(token);
      return response;
  }*/
  
  
}
