package com.webservice.web.oauth.info;

import java.util.Map;

import com.webservice.web.oauth.entity.ProviderType;
import com.webservice.web.oauth.info.impl.KakaoOAuth2UserInfo;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
