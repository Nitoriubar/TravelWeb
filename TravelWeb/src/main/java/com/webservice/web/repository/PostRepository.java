package com.webservice.web.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.webservice.web.model.Post;

@Repository
public interface PostRepository {
	int insert(Post post);
	int update(Post post);
	int getCurrentPostNo();
	List<Post> getPostList(@Param("searchValue") String searchValue,
			@Param("startRowIndex") int startRowIndex);
	Post getPost(int postNo);
	int updateDeleted(int postNumber);
}
