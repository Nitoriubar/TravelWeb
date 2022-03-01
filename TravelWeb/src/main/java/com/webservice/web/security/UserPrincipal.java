package com.webservice.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.webservice.web.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//������ ��ť��Ƽ�� �α��� ��û�� ����ä�� �α����� �����ϰ� �Ϸᰡ �Ǹ� UserDetails Ÿ���� ������Ʈ��
//������ ��ť��Ƽ�� ������ ���� ����ҿ� ������ ���ش�.
@RequiredArgsConstructor
@Getter
public class UserPrincipal implements OAuth2User, UserDetails {
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes; //OAuth �����ڷ� ���� ���� ȸ�� ����

    private User user;
    
    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    //������ ����Ǿ����� (true: ������� ����)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //������ ����ִ��� (true: ������� ����)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //�н����尡 ������� �ʾҴ��� (true: ������� ����)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    //������ Ȱ��ȭ�Ǿ� �ִ��� (true: Ȱ��ȭ)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
