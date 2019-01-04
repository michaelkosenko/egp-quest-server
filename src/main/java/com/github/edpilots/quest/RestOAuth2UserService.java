package com.github.edpilots.quest;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.web.client.RestOperations;

import lombok.Builder;
import lombok.Data;

public class RestOAuth2UserService
        implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private RestOperations restOperations;
    private ParameterizedTypeReference<Map<String, Object>> typeReference;
    private DiscordProperties discordProperties;

    public RestOAuth2UserService(RestOperations restOperations, DiscordProperties discordProperties) {
        this.restOperations = restOperations;
        this.discordProperties = discordProperties;

    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {
        
        System.out.println(">>> " + discordProperties);
        
        
        String userInfoUrl = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s",
                userRequest.getAccessToken().getTokenValue()));
        headers.set(HttpHeaders.USER_AGENT, SecurityConfiguration.USER_AGENT);

        typeReference = new ParameterizedTypeReference<Map<String, Object>>() {
        };

        ResponseEntity<Map<String, Object>> responseEntity = restOperations
                .exchange(userInfoUrl, HttpMethod.GET,
                        new HttpEntity<>(headers), typeReference);

        Map<String, Object> userAttributes = responseEntity.getBody();
        
        ParameterizedTypeReference<Object> typeObject = new ParameterizedTypeReference<Object>() {};
        ParameterizedTypeReference<List<Map<String, Object>>> typeList = new ParameterizedTypeReference<List<Map<String, Object>>>() {};
        
        String id = (String) userAttributes.get("id");
        
        HttpHeaders headers2 = new HttpHeaders();
        headers2.set(HttpHeaders.AUTHORIZATION, String.format("Bot %s", discordProperties.getBot().getToken()));
        headers2.set(HttpHeaders.USER_AGENT, SecurityConfiguration.USER_AGENT);
        
        
        ResponseEntity<List<Map<String, Object>>> rolesResponse = restOperations.exchange("https://discordapp.com/api/guilds/499601264892444684/roles", HttpMethod.GET,
                new HttpEntity<>(headers2), typeList);
        List<Map<String, Object>> allRoles = rolesResponse.getBody();

        Map<String, String> allRoles2 = new HashMap<>();
        allRoles.stream().forEach(role -> allRoles2.put((String) role.get("id"), (String) role.get("name")));
        

        ResponseEntity<Map<String, Object>> guildsResponse = restOperations.exchange("https://discordapp.com/api/guilds/499601264892444684/members/" + id, HttpMethod.GET,
                new HttpEntity<>(headers2), typeReference);
        
        Map<String, Object> guildMember = guildsResponse.getBody();
        Map<String, Object> user = (Map<String, Object>) guildMember.get("user");
        List<String> roles = (List<String>) guildMember.get("roles");
        
        
//        Set<GrantedAuthority> authorities = Collections
//                .singleton(new OAuth2UserAuthority(userAttributes));
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.stream().map(roleId -> new SimpleGrantedAuthority(allRoles2.get(roleId))).forEach(authorities::add);

        return new DefaultOAuth2User(authorities, userAttributes,
                userRequest.getClientRegistration().getProviderDetails()
                        .getUserInfoEndpoint().getUserNameAttributeName());

    }
    
    @Data
    @Builder
    static class Role {
        private String id;
        private String name;
    }

}
