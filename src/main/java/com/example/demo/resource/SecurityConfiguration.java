package com.example.demo.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	KitchenService kService;

	// providing access to some type of urls for reg ,login, css, jss,html images etc
  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
	  http
	  			.authorizeRequests()
	  					.antMatchers(
	  							"/",
	  							"/register-page=1",
	  							"/registration**",
	  							"/js",
	  							"/css",
	  							"/img",
	  							"/webjars/**").permitAll()
	  						.anyRequest().authenticated()
	  				.and()
	  					.formLogin()
	  						.defaultSuccessUrl("/register-page=2")
	  						.loginPage("/login")
	  							.permitAll()
	  				.and()
	  					.logout()
	  							.invalidateHttpSession(true)
	  							.clearAuthentication(true)
	  							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	  							.logoutSuccessUrl("/?logout")
	  				.permitAll();

  }
  
@Bean
public BCryptPasswordEncoder passwordEncoder()
{
	return new BCryptPasswordEncoder();
}
@Bean
public DaoAuthenticationProvider authencationProvider()
{
	DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	auth.setUserDetailsService(kService);
	// you can add more servics here...
	auth.setPasswordEncoder(passwordEncoder());
	return auth;
}
@Override
protected void configure(AuthenticationManagerBuilder auth)
{
	auth.authenticationProvider(authencationProvider());
}
}
