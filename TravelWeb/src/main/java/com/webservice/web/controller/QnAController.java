package com.webservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.webservice.web.security.QnAService;

@RequiredArgsConstructor
@Controller
public class QnAController {
	
	private final QnAService qnaService;

    /**
     * QnA 페이지
     */
    @GetMapping("/auth/board/qna")
    public String qna() {
        return "TopMenu/board/qna/QnA";
    }


    /**
     * 글작성 페이지
     */
    @GetMapping("/board/qna/save")
    public String save() {
        return "TopMenu/board/qna/qna-save";
    }

    /**
     * 글상세 페이지
     */
    // 주소 뒤에 {id} 이렇게 id를 받을 때는 @PathVariable을 사용하면 주소의 id로 받습니다.
    @GetMapping("/board/qna/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board", qnaService.detail(id));
        qnaService.updateCount(id);
        return "TopMenu/board/qna/qna-detail";
    }

    /**
     * 글 수정 페이지
     */
    @GetMapping("/board/qna/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("board", qnaService.detail(id));
        return "TopMenu/board/qna/qna-update";
    }
}
