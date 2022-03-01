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
     * QnA ������
     */
    @GetMapping("/auth/board/qna")
    public String qna() {
        return "TopMenu/board/qna/QnA";
    }


    /**
     * ���ۼ� ������
     */
    @GetMapping("/board/qna/save")
    public String save() {
        return "TopMenu/board/qna/qna-save";
    }

    /**
     * �ۻ� ������
     */
    // �ּ� �ڿ� {id} �̷��� id�� ���� ���� @PathVariable�� ����ϸ� �ּ��� id�� �޽��ϴ�.
    @GetMapping("/board/qna/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board", qnaService.detail(id));
        qnaService.updateCount(id);
        return "TopMenu/board/qna/qna-detail";
    }

    /**
     * �� ���� ������
     */
    @GetMapping("/board/qna/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("board", qnaService.detail(id));
        return "TopMenu/board/qna/qna-update";
    }
}
