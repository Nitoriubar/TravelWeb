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
    // 댓글을 저장할 때는 QnA의 Id 값을 가져와야 한다.
    // 그래서 QnA를 영속화시켜서 QnA와 User를 저장
    public void replySave(Long QnAId, Reply reply, User user) {
        QnA qna = qnaRepository.findById(QnAId).orElseThrow(() -> new IllegalArgumentException("해당 QnAId가 없습니다. id=" + QnAId));

        reply.save(qna, user);
        replyRepository.save(reply);
    }

    @Transactional
    public void replyDelete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
