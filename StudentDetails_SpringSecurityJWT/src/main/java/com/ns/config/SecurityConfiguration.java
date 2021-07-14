package com.ns.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ns.repository.StudentRepository;
import com.ns.service.jwtStudentService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final StudentRepository studentRepository;

	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;

	@Autowired
	private jwtStudentService jwtStudentService;

	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter1;

	public SecurityConfiguration(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		((Object) auth).jwtStudentService(id -> studentRepository.findByStudentId(id)
				.orElseThrow(() -> new UsernameNotFoundException(format("User: %s, not found", id))));
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		((Object) auth).jwtStudentService(jwtStudentService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable()

				.authorizeRequests().antMatchers("/authenticate", "/register").permitAll().

				anyRequest().authenticated()and().

				exceptionHandling().authorizationFilter(jwtAuthorizationFilter).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
