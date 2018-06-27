package com.manolo.sell.inventory;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * if security on classpath: default security configuration, generating userDetailsService and actuator configuration too.
 * To switch off default sec configuration:  add own WebSecurityConfigurerAdapter (actuator access rules configured here then)
 * 
 * @author manolo
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests().antMatchers("/actuator/**", "/api/**").permitAll()
			.and()
				.csrf().disable();

	}
}
