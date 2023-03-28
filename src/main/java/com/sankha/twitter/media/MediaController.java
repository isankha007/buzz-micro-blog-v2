package com.sankha.twitter.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sankha.twitter.fileupload.FilesStorageService;
import com.sankha.twitter.response.ApiResponse;

@RestController
@RequestMapping("/tweets")
public class MediaController {
	  	@Autowired
	  	FilesStorageService storageService;
	  
	  	@Autowired
		private ApiResponse apiResponse;
	  	
	  	@Autowired
	  	private MediaService mediaService;
	  
	 // @PostMapping("/upload")
	  @PostMapping(path = "/tweet/upload", produces = "application/json")
	  public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
		  String message = "";
		    try {
		      storageService.save(file);
		      Media savedMedia = mediaService.createMedia(file.getOriginalFilename());
		      message = "Uploaded the file successfully: " + file.getOriginalFilename();
		      
		      apiResponse.setMessage(message);
			  apiResponse.setData(savedMedia);	    
			   
				return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.OK);
		    } catch (Exception e) {
		      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
		      apiResponse.setMessage(message);
			  return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.EXPECTATION_FAILED);

		     // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		    
		    
		    }	 
		    
	  }
		  

}
