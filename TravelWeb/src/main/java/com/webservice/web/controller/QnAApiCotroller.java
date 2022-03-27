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
     * ���ۼ� API
     */
    @PostMapping("/api/v1/qna")
    // @PostMapping�̹Ƿ� @RequestBody�� ���ٿ��־�� �Ѵ�.
    // � ����ڰ� �Խñ��� �ۼ��ϴ��� �˱� ���� @AuthenticationPrincipal ������ �Ķ���ͷ� �޴´�.
    public Long save(@RequestBody QnASaveRequest qnaSaveRequest,
                     @AuthenticationPrincipal UserPrincipal userprincipal) {
        return qnaService.save(qnaSaveRequest, userprincipal.getUser());
    }

    /**
     * �ۻ��� API
     */
    @DeleteMapping("/api/v1/qna/{id}")
    // id���� �ּҿ� �ޱ� ���� @PathVariable
    public Long deleteById(@PathVariable Long id) {
    	qnaService.deleteById(id);
        return id;
    }
    
    /**
     * �ۼ��� API
     */
    @PutMapping("/api/v1/qna/{id}")
    public Long update(@PathVariable Long id, @RequestBody QnAUpdateRequest qnaUpdateRequest) {
        return qnaService.update(id, qnaUpdateRequest);
    }

}
