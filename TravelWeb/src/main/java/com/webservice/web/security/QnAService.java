package com.webservice.web.security;

import com.webservice.web.model.QnA;
import com.webservice.web.model.User;
import com.webservice.web.payload.QnASaveRequest;
import com.webservice.web.payload.QnAUpdateRequest;
import com.webservice.web.repository.QnARepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QnAService {
	
	private final QnARepository qnaRepository;
	/**
     * 글작성 로직
     */
    @Transactional
    public Long save(QnASaveRequest qnaSaveRequest, User user) {
    	qnaSaveRequest.setUser(user);
        return (long) qnaRepository.save(qnaSaveRequest.toEntity()).getId();
    }

    /**
     * 글상세 로직
     */
    @Transactional(readOnly = true)
    public QnA detail(Long id) {
        return qnaRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다. id=" +id);
                });
    }
    /**
     * 글삭제 로직
     */
    // JpaRepository의 deleteById는 void타입
    @Transactional
    public void deleteById(Long id) {
    	qnaRepository.deleteById(id);
    }


    /**
     * 글수정 로직
     */
    //먼저 boardRepository.findById(id)로 찾아서 Board를 영속화시킨다.
    // 그러면 영속성 컨텍스트에 Board 객체가 담아진다.
    // 그리고 나서 Board의 값을 변경시키면 Service가 종료되는 시점에 트랜잭션이 종료되고 더티체킹이 일어난다.
    @Transactional
    public Long update(Long id, QnAUpdateRequest boardUpdateRequestDto) {
        QnA qna = qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        qna.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return id;
    }

    /**
     * 글 조회수 로직
     */
    @Transactional
    public int updateCount(Long id) {
        return qnaRepository.updateCount(id);
    }

    /**
     * 글목록 로직 (페이징)
     */
    @Transactional(readOnly = true)
    public Page<QnA> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        return qnaRepository.findByTitleContainingOrContentContaining(title, content, pageable);
    }
}
