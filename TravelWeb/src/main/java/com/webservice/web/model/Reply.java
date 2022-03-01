package com.webservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import com.webservice.web.model.User;
import com.webservice.web.model.BaseTimeEntity;
import com.webservice.web.model.QnA;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@DynamicUpdate// 변경 필드반 반영
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private QnA qna;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public void save(QnA qna, User user) {
        this.qna = qna;
        this.user = user;
    }
}
