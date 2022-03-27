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

    // qnaId 는 @PathVariable 통해서, Reply는 JSON으로 보내주고,
    // User 정보는 @AuthenticationPrincipal로 보내준다.

    @PostMapping("/api/v1/qna/{qnaId}/reply")
    public void save(@PathVariable Long boardId,
                     @RequestBody Reply reply,
                     @AuthenticationPrincipal UserPrincipal userPrincipal) {
        replyService.replySave(boardId, reply, userPrincipal.getUser());
    }

    //댓글 삭제 맵핑
    @DeleteMapping("/api/v1/qna/{qnaId}/reply/{replyId}")
    public void delete(@PathVariable Long replyId) {
        replyService.replyDelete(replyId);
    }
}
