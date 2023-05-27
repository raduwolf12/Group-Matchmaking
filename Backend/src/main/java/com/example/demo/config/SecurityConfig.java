package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.JwtTokenFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			

			 @Override
			    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			        User user = userRepository.findByEmail(username)
			                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

			        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole().name());

			        return new org.springframework.security.core.userdetails.User(
			                user.getEmail(),
			                user.getPassword(),
			                authorities
			        );
			    }
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
            .requestMatchers("/auth/login").permitAll() // Allow access to login endpoint
            .requestMatchers("/admin/**").hasRole("PROFESSOR") // Restrict access to admin endpoints to PROFESSOR role
            .anyRequest().authenticated();
//        .and()
//        .httpBasic();
//		
		
//		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and().authorizeRequests().requestMatchers("/auth/login", "/docs/**", "/users").permitAll().anyRequest()
//				.authenticated();

		http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		});

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
