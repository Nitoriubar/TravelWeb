package com.webservice.web.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import com.webservice.web.model.BaseTimeEntity;
import com.webservice.web.model.Reply;
import com.webservice.web.model.User;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QnA {
	
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; //auto_increment
	
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob //��뷮 �������� ��� ���
	private String content; //���ӳ�Ʈ ���̺귯�� �����.(<html>�±װ� ������ ������ ��. �뷮�� ŭ.)
	
	private int count; //��ȸ��
	
	@ManyToOne(fetch = FetchType.EAGER)
	// �Խñ��� �ۼ��� �� ���� �ۼ��ߴ��� �˾ƾ� �ϱ� ������ User ���̺�� ����
	// Java�ڵ�� ��ü�� �����ϰ� �Ǹ� ORM�������� �˾Ƽ� ����
	// id���� ���� �����ϱ� id������ foreignŰ�� ����
	@JoinColumn(name = "userId") // foreignŰ�� �÷��� ����
	private User user;

	    // �� ���� �޼ҵ�
	    // JPA���� udpate�� ������ ���� ���Ӽ� ���ؽ�Ʈ�� �ִ� ���� �񱳸� �ؼ� ����� ���� ������ �� ����� ���� update �����ش�.
	    // �̰��� ���氨���� �Ͽ� ��ġüŷ�̶� �θ���.
	    // ��, Entity ��ü�� ���� ��������ָ� ��Ƽüŷ�� �Ͼ�ϴ�. (Update �������� ���� �ʿ䰡 ����!)
	public void update(String title, String content) {
	    this.title = title;
	    this.content = content;
	}

	// ��� ����
	@OrderBy("id desc") // ��� �ۼ��� �ֱ� ������ �� �� �ֵ��� ����

	    // ���� ���� �߻� �ذ���
	    // Board ���̺� ��۸���Ʈ�� �߰��ϰڴٴ� �ǹ�
	    // DB���� �ϳ��� raw �����Ϳ� �ϳ��� ���� �� �� �ִ�.
	    // ���� ���� ���� �����Ͱ� ���ٸ� ���ڼ��� ������.
	    // �׷��� replyList�� DB�� FK�� �����Ǹ� �ȵǱ� ������ mappedBy�� ����Ѵ�.
	    //mppedBy : ���������� ������ �ƴϹǷ� DB�� FK�� �ƴϴ� ��� ��
	    //@OneToMany�� ����Ʈ fetch�� Lazy�Դϴ�. �̰��� Eager�� ����
	@JsonIgnoreProperties({"board", "user"}) //JsonIgnoreProperties�� �ɾ��ָ� reply �ȿ��� ȣ���� �� �Ұ� �ɶ� board�� getter ȣ�� ����
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Reply> replyList;
	
}
