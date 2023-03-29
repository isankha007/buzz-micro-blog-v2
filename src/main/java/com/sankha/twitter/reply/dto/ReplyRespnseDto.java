package com.sankha.twitter.reply.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sankha.twitter.tweet.Tweet;
import com.sankha.twitter.user.UserEntity;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
public class ReplyRespnseDto {
    private Long replyId;

    private String text;
    private Timestamp created;
    private Timestamp updated;

    private UserEntity replyAuthor;

    private Tweet tweet;
}
