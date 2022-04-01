package com.webservice.web.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.webservice.web.exception.FileTransferFailedException;
import com.webservice.web.model.Hashtag;
import com.webservice.web.model.MakerImage;
import com.webservice.web.model.Post;
import com.webservice.web.model.User;
import com.webservice.web.repository.HashtagRepository;
import com.webservice.web.repository.MakerImageRepository;
import com.webservice.web.repository.PostRepository;

@Service
public class PostService implements PostServiceItf{
	private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private MakerImageRepository makerImageRepository;
	
	@Autowired
	private HashtagRepository hashtagRepository;
	
	@Transactional
	@Override
	public void insertPost(User writer, Post post, MultipartFile files) {
		
		String webAppHome = environment.getProperty("fileupload-localDir");
		String imagePath = environment.getProperty("fileupload-image-path");
		String makerImageUploadFolder = environment.getProperty("fileupload-post-path");
		
		/* Post Insert */
		post.setWriter(writer.getId());
		postRepository.insert(post);
		
		/* PostImage Create */
		int postNo = postRepository.getCurrentPostNo();
		File makerImageFile = new File(webAppHome + imagePath + makerImageUploadFolder + "/" + postNo + "/" + files.getOriginalFilename()); 
		MakerImage makerImage = new MakerImage();
		makerImage.setPath(imagePath + makerImageUploadFolder + "/" + postNo);
		makerImage.setName(FilenameUtils.getBaseName(makerImageFile.getName()));
		makerImage.setExtension(FilenameUtils.getExtension(files.getOriginalFilename()));
		makerImage.setPostNo(postNo);
		
		/* Maker Image Insert */
		makerImageRepository.insert(makerImage);
		
		/* Hashtag Insert */
		List<Hashtag> hashtags = post.getHashtags();
		for(Hashtag hashtag : hashtags) {
			hashtag.setPostNo(postNo);
		}
		hashtagRepository.insert(hashtags);
		
		/* MakerImageFile Transfer */
		try {
			files.transferTo(makerImageFile);
		}catch (Exception e) {
			throw new FileTransferFailedException(e);
		}
	}

	@Override
	public void updatePost(User writer, Post post) {
		post.setWriter(writer.getId());
		postRepository.update(post);
		
		hashtagRepository.deleteAllByPostNo(post.getNo());
		List<Hashtag> hashtags = post.getHashtags();
		for(Hashtag hashtag : hashtags) {
			hashtag.setPostNo(post.getNo());
		}
		hashtagRepository.insert(hashtags);
	}

	@Override
	public void deletePost(int postNumber) {
		hashtagRepository.deleteAllByPostNo(postNumber);
		makerImageRepository.deleteByPostNo(postNumber);
		postRepository.updateDeleted(postNumber);
	}

	@Override
	public List<Hashtag> parseHashtag(String hashtagStr) {
		List<Hashtag> hashtags = new ArrayList<>();
		List<String> hashtagNames = Arrays.asList(hashtagStr.split("#"));
		for(int i = 1; i < hashtagNames.size(); i++) {
			Hashtag hashtag = new Hashtag();
			hashtag.setName("#"+hashtagNames.get(i));
			hashtags.add(hashtag);
		}
		return hashtags;
	}

}
