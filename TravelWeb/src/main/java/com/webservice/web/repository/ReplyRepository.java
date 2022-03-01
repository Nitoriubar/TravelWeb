package com.webservice.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.web.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}