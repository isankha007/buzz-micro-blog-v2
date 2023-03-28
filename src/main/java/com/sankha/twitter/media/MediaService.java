package com.sankha.twitter.media;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankha.twitter.util.TimestampUtil;

@Service
public class MediaService {
  @Autowired
  private MediaRepository repository;
  
  @Autowired
  TimestampUtil timestampUtil;
  
  public Media createMedia(String fileName) {
	  Media newMedia =new Media();
	  newMedia.setFileName(fileName);
	  Timestamp currentTimestamp = timestampUtil.currentTimestamp();
	  newMedia.setCreated(currentTimestamp);
	  newMedia.setUpdated(currentTimestamp);
	  return repository.save(newMedia);
  }
}
