package com.executor;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from user_roles where username=?");
	}	
	
  /* @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication()
         .withUser("sunil").password("pass123").roles("USER")
         .and()
         .withUser("admin").password("pass123").roles("ADMIN");
   }
*/
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	   http.authorizeRequests()
		.antMatchers("/books/**").access("hasRole('ROLE_PRINCIPAL') or hasRole('ROLE_LIBRARIAN')")
		.antMatchers("/subjects/**").access("hasRole('ROLE_PRINCIPAL')")
		.and()
		  .formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check").failureUrl("/login?error")
		  .usernameParameter("username").passwordParameter("password")
		.and()
		  .logout().logoutSuccessUrl("/login?logout")
		.and()
		  .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf();
   }
}