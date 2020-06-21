package org.emp.emphealth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public void configure(final AuthenticationManagerBuilder auth)
			throws Exception{
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/","/profil", "/patients", "/patients/*", "/capteurs", "/capteurs/*", "/teleconsultation", "/rendezvous","/stats","/ajout","/inbox","/compose","/vital/*", "/message/*", "/prescription/*", "/examen/*").authenticated()
		.antMatchers(HttpMethod.POST,"/observation").permitAll()
		.antMatchers(HttpMethod.POST,"/refresh").permitAll()
		
		// equivalent to <http auto-config="true">
		.and().formLogin()
					.loginPage("/login/form")
					.loginProcessingUrl("/login")
					.failureUrl("/login/form?error")
					.usernameParameter("username")
					.passwordParameter("password")
					.defaultSuccessUrl("/")
		.and().httpBasic()
		.and().logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login/form?logout")
		.and()
		.exceptionHandling().accessDeniedPage("/403")
		.and()
		.rememberMe().tokenValiditySeconds(2592000)
		// CSRF is enabled by default (will discuss later)
		.and().csrf().disable();
		http.headers().frameOptions().disable();
	}
	@Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
