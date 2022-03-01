package com.webservice.web.payload;

import com.webservice.web.model.QnA;
import com.webservice.web.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnASaveRequest {
	  private String title;
	  private String content;
	  private int count;
	  private User user;

	  public QnA toEntity() {

	       return QnA.builder()
	              .title(title)
	              .content(content)
	              .count(0)
	              .user(user)
	              .build();
	    }

	  public void setUser(User user) {
	      this.user = user;
	  }
}
