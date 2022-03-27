package com.webservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.webservice.web.model.Reply;
import com.webservice.web.security.UserPrincipal;
import com.webservice.web.service.ReplyService;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {

	private final ReplyService replyService;

    // qnaId �� @PathVariable ���ؼ�, Reply�� JSON���� �����ְ�,
    // User ������ @AuthenticationPrincipal�� �����ش�.

    @PostMapping("/api/v1/qna/{qnaId}/reply")
    public void save(@PathVariable Long boardId,
                     @RequestBody Reply reply,
                     @AuthenticationPrincipal UserPrincipal userPrincipal) {
        replyService.replySave(boardId, reply, userPrincipal.getUser());
    }

    //��� ���� ����
    @DeleteMapping("/api/v1/qna/{qnaId}/reply/{replyId}")
    public void delete(@PathVariable Long replyId) {
        replyService.replyDelete(replyId);
    }
}
