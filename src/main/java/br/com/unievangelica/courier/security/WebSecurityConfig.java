package br.com.unievangelica.courier.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	  @Override
      public void configure(WebSecurity web) throws Exception {
	    	web.ignoring().antMatchers("/","/index.html","/admin/**", "/app/**");

      }
	  
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http.headers().cacheControl();

	        http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/").permitAll()
	        .and();
	    }
}
