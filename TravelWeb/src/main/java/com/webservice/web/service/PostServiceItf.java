package com.webservice.web.service;

import java.util.List;

import com.webservice.web.model.Hashtag;
import com.webservice.web.model.Post;
import com.webservice.web.model.User;

import org.springframework.web.multipart.MultipartFile;

public interface PostServiceItf {
	void insertPost(User user, Post post, MultipartFile files);
	void updatePost(User user, Post post);
	void deletePost(int postNumber);
	List<Hashtag> parseHashtag(String hashtag);
}
