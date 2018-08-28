package com.github.edpilots.quest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    public static final String USER_AGENT = "QuestServer";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/login**")
            .permitAll()
            .antMatchers("/pilot**")
            .authenticated()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2Login()
                .tokenEndpoint().accessTokenResponseClient(new RestOAuth2AccessTokenResponseClient(restOperations()))
                .and()
                .userInfoEndpoint().userService(new RestOAuth2UserService(restOperations()));
    }
    
    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

}
