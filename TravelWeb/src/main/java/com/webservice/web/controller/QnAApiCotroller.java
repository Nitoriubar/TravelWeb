package com.webservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.webservice.web.payload.QnASaveRequest;
import com.webservice.web.payload.QnAUpdateRequest;
import com.webservice.web.security.UserPrincipal;
import com.webservice.web.service.QnAService;

@RequiredArgsConstructor
@RestController
public class QnAApiCotroller {
	
	private final QnAService qnaService;
	
	/**
     * 글작성 API
     */
    @PostMapping("/api/v1/qna")
    // @PostMapping이므로 @RequestBody를 꼭붙여주어야 한다.
    // 어떤 사용자가 게시글을 작성하는지 알기 위해 @AuthenticationPrincipal 정보를 파라미터로 받는다.
    public Long save(@RequestBody QnASaveRequest qnaSaveRequest,
                     @AuthenticationPrincipal UserPrincipal userprincipal) {
        return qnaService.save(qnaSaveRequest, userprincipal.getUser());
    }

    /**
     * 글삭제 API
     */
    @DeleteMapping("/api/v1/qna/{id}")
    // id값을 주소에 받기 위해 @PathVariable
    public Long deleteById(@PathVariable Long id) {
    	qnaService.deleteById(id);
        return id;
    }
    
    /**
     * 글수정 API
     */
    @PutMapping("/api/v1/qna/{id}")
    public Long update(@PathVariable Long id, @RequestBody QnAUpdateRequest qnaUpdateRequest) {
        return qnaService.update(id, qnaUpdateRequest);
    }

}
