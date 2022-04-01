package com.webservice.web.model;

import lombok.Data;
import lombok.var;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Post{

    private int no;
    private int visitor_cnt;
    private int post_lock;
    private String title;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private long writer;
//    private MakerImage markerImage;
    private var marker_img_path;
    private int marker_img_size;
    private var marker_img_type;
    private List<Hashtag> hashtags;
//  private List<PostLike> postLikes;
//  private List<PostReply> postReplies;
}