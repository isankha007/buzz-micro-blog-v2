package com.sankha.twitter;

import java.util.Collections;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sankha.twitter.fileupload.FilesStorageService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@ComponentScan(basePackageClasses = TweetCotroller.class)
//@ComponentScan(basePackages = "com.sankha.twitter.tweet")
public class BuzzMicroBlogV2Application implements CommandLineRunner{
	
	@Resource
	 FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(BuzzMicroBlogV2Application.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/tweets/**"))
				.build()
				.apiInfo(apiDetails());
		
	}
	
	private ApiInfo apiDetails()
	{
		return new ApiInfo("Buzz API"
			, "Twitter clone in Spring Boot. Allows user creation, user can follow another user, tweet and comment."
			, "1.0"
			, "Free to use"
			, new springfox.documentation.service.Contact("sankhadeep chatterjee","" ,"sankhadeep2007@gmail.com")
			, "http://google.com"
			,null, Collections.emptyList());
	}
	
	  @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	    storageService.init();
	}

}
