package com.sankha.twitter.user;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sankha.twitter.response.ApiResponse;
import com.sankha.twitter.user.dto.TweetersList;

@RestController
@RequestMapping("/tweets")
public class UserController {
	@Autowired
    private UserService userService;
	@Autowired
	private ApiResponse apiResponse;
	
	@GetMapping(path = "/user/list", produces = "application/json")
	public ResponseEntity<Object> listOfUsers(Authentication authentication)
	{
		TweetersList userList = userService.listUsers(authentication);
	    apiResponse.setMessage("User Lists!");
	    apiResponse.setData(userList);
		
		return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
	}
    
    /*@PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(
            @RequestBody CreateUserRequestDto request
    ) {
        var createdUser = usersService.createUser(request);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getUserId())).body(createdUser);
    }*/

}
