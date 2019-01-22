package com.github.edpilots.quest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(DiscordProperties.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    public static final String USER_AGENT = "QuestServer (http://localhost:8080, 1)";

    @Autowired
    private DiscordProperties discordProperties;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/login**", "/edmc")
            .permitAll()
            .antMatchers("/pilot**")
            .authenticated()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable()
            .logout().logoutSuccessUrl("/")
            .and()
            .oauth2Login()
                .tokenEndpoint().accessTokenResponseClient(new RestOAuth2AccessTokenResponseClient(restOperations()))
                .and()
                .userInfoEndpoint().userService(new RestOAuth2UserService(restOperations(), discordProperties));
            
    }
    
    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

}
