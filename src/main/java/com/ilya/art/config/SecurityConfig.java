package com.ilya.art.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String USERS_BY_USERNAME = "select username, password, 'true' " + "from USERS "
			+ "where username = ?";
	private static final String AUTHS_BY_USERNAME = "select username, role" + "from USERS where username = ?;";

	@Resource(name = "AuthRegDataSource")
	DataSource authRegDataSource;

	/*
	 * @Bean(name = "filterMultipartResolver") public MultipartResolver
	 * multipartResolver() throws IOException { CommonsMultipartResolver
	 * filterMultipartResolver = new CommonsMultipartResolver();
	 * filterMultipartResolver.setUploadTempDir(new
	 * FileSystemResource("/im"));
	 * filterMultipartResolver.setMaxUploadSize(2097152);
	 * filterMultipartResolver.setMaxInMemorySize(0); return
	 * filterMultipartResolver; }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER", "ADMIN").and().withUser("user")
				.password("user").roles("USER").and().and().jdbcAuthentication().dataSource(authRegDataSource)
				.passwordEncoder(new BCryptPasswordEncoder())
				;

	}

	@Override
	
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").defaultSuccessUrl("/").and().rememberMe().tokenValiditySeconds(2419200)
		.and()
		.authorizeRequests().antMatchers("/panel/*").authenticated().anyRequest().permitAll().and().formLogin()
				.and().httpBasic().and().csrf().disable();

	}

}
