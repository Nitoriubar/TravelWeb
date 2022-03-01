package com.webservice.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webservice.web.model.QnA;

public interface QnARepository extends JpaRepository<QnA, Long>{

	@Modifying
    @Query("update Board p set p.count = p.count + 1 where p.id = :id")
    int updateCount(Long id);

    // Containing�̶�� Ű���带 ����ϸ� JPA���� LIKE������ ����
    // �����̳� �������� �˻��� �� �ְ�
    Page<QnA> findByTitleContainingOrContentContaining(@Param("title") String title,
                                                         @Param("content") String content, Pageable pageable);
    
}
