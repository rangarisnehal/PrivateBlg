package com.snehal.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.snehal.blog.security.CustomUserDetailService;
import com.snehal.blog.security.JwtAuthenticatinFilter;
import com.snehal.blog.security.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticatinFilter filter;

	@Autowired
	private CustomUserDetailService customUserDetailService;

//	protected void configure(HttpSecurity http) throws Exception {

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//    	http.csrf().disable()
//    	.authorizeHttpRequests()
//    	.anyRequest()
//    	.authenticated()
//    	.and()
//    	.exceptionHandling().authenticationEntryPoint(this.point)
//    	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    	
//    	http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);

//		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests().requestMatchers("/login").authenticated()
//				.requestMatchers("/auth/login").permitAll().anyRequest().authenticated().and()
//				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
		
		http.csrf(csrf-> csrf.disable()).cors(cors-> cors.disable())
		.authorizeHttpRequests(auth-> auth.requestMatchers("/api/**").authenticated().requestMatchers("auth/login")
				.permitAll().anyRequest().authenticated()).exceptionHandling(ex-> ex.authenticationEntryPoint(point))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

}
