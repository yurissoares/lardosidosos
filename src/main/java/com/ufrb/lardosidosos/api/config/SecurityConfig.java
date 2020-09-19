//package com.ufrb.lardosidosos.api.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//
//import com.ufrb.lardosidosos.domain.service.CustomUsuarioDetailService;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private CustomUsuarioDetailService customUsuarioDetailService;
//	
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests()
////			.anyRequest().authenticated()
////			.and()
////			.httpBasic()
////			.and()
////			.csrf().disable();
////	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//			.and().csrf().disable()
//			.authorizeRequests()
//			.antMatchers(HttpMethod.GET, SecurityConstants.SIGN_UP_URL).permitAll()
//			.and()
//			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
//			.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUsuarioDetailService));
//	
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUsuarioDetailService).passwordEncoder(new BCryptPasswordEncoder());
//	}
//	
//}
