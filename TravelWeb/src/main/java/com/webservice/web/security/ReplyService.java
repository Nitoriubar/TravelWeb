package com.webservice.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.webservice.web.model.QnA;
import com.webservice.web.model.Reply;
import com.webservice.web.model.User;
import com.webservice.web.repository.QnARepository;
import com.webservice.web.repository.ReplyRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final QnARepository qnaRepository;

    @Transactional
    // ����� ������ ���� QnA�� Id ���� �����;� �Ѵ�.
    // �׷��� QnA�� ����ȭ���Ѽ� QnA�� User�� ����
    public void replySave(Long QnAId, Reply reply, User user) {
        QnA qna = qnaRepository.findById(QnAId).orElseThrow(() -> new IllegalArgumentException("�ش� QnAId�� �����ϴ�. id=" + QnAId));

        reply.save(qna, user);
        replyRepository.save(reply);
    }

    @Transactional
    public void replyDelete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
