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
     * ���ۼ� ����
     */
    @Transactional
    public Long save(QnASaveRequest qnaSaveRequest, User user) {
    	qnaSaveRequest.setUser(user);
        return (long) qnaRepository.save(qnaSaveRequest.toEntity()).getId();
    }

    /**
     * �ۻ� ����
     */
    @Transactional(readOnly = true)
    public QnA detail(Long id) {
        return qnaRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("�� �󼼺��� ���� : ���̵� ã�� �� �����ϴ�. id=" +id);
                });
    }
    /**
     * �ۻ��� ����
     */
    // JpaRepository�� deleteById�� voidŸ��
    @Transactional
    public void deleteById(Long id) {
    	qnaRepository.deleteById(id);
    }


    /**
     * �ۼ��� ����
     */
    //���� boardRepository.findById(id)�� ã�Ƽ� Board�� ����ȭ��Ų��.
    // �׷��� ���Ӽ� ���ؽ�Ʈ�� Board ��ü�� �������.
    // �׸��� ���� Board�� ���� �����Ű�� Service�� ����Ǵ� ������ Ʈ������� ����ǰ� ��Ƽüŷ�� �Ͼ��.
    @Transactional
    public Long update(Long id, QnAUpdateRequest boardUpdateRequestDto) {
        QnA qna = qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("�ش� id�� �����ϴ�. id=" + id));
        qna.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return id;
    }

    /**
     * �� ��ȸ�� ����
     */
    @Transactional
    public int updateCount(Long id) {
        return qnaRepository.updateCount(id);
    }

    /**
     * �۸�� ���� (����¡)
     */
    @Transactional(readOnly = true)
    public Page<QnA> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        return qnaRepository.findByTitleContainingOrContentContaining(title, content, pageable);
    }
}
