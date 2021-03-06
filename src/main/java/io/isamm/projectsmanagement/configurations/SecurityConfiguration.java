package io.isamm.projectsmanagement.configurations;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.isamm.projectsmanagement.components.CORSFilter;
import io.isamm.projectsmanagement.components.JwtAuthenticationFilter;
import io.isamm.projectsmanagement.components.JwtAuthenticationProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final CORSFilter corsFilter;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationProvider jwtAuthenticationProvider;
	
	@Autowired
	public SecurityConfiguration(CORSFilter corsFilter, JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationProvider jwtAuthenticationProvider) {
		super();
		this.corsFilter = corsFilter;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.jwtAuthenticationProvider = jwtAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.jwtAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
        
        http.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .antMatchers("/auth/**/*").permitAll()
            .antMatchers(HttpMethod.OPTIONS , "/*/**").permitAll()
            .antMatchers("/api/v1" + "/**/*").authenticated();
                
        http.addFilterBefore(this.corsFilter, ChannelProcessingFilter.class);
        
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        http.exceptionHandling()
            .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"));
		
	}
	
}
