package com.webservice.web.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.webservice.web.model.Hashtag;

@Repository
public interface HashtagRepository {
	void insert(List<Hashtag> hashtags);
	int deleteAllByPostNo(int postNo);
}