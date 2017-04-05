#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

	private static final String USERS_BY_USERNAME = "select email, user_password, 'true' " + "from users "
			+ "where email = ?";
	private static final String AUTHS_BY_USERNAME = "select email, 'ADMIN'" + "from users where email = ?;"; // REDO

	@Autowired
	DataSource dataSource;

	/*
	 * @Bean(name = "filterMultip${artifactId}Resolver") public Multip${artifactId}Resolver
	 * multip${artifactId}Resolver() throws IOException { CommonsMultip${artifactId}Resolver
	 * filterMultip${artifactId}Resolver = new CommonsMultip${artifactId}Resolver();
	 * filterMultip${artifactId}Resolver.setUploadTempDir(new FileSystemResource("/im"));
	 * filterMultip${artifactId}Resolver.setMaxUploadSize(2097152);
	 * filterMultip${artifactId}Resolver.setMaxInMemorySize(0); return
	 * filterMultip${artifactId}Resolver; }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER", "ADMIN").and().withUser("user")
				.password("user").roles("USER").and().and().jdbcAuthentication().dataSource(dataSource)
				.passwordEncoder(new BCryptPasswordEncoder()).usersByUsernameQuery(USERS_BY_USERNAME)
				.authoritiesByUsernameQuery(AUTHS_BY_USERNAME);

	}

	@Override

	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").defaultSuccessUrl("/").and().rememberMe().tokenValiditySeconds(2419200)
				.and().authorizeRequests().antMatchers("/panel/*").authenticated().anyRequest().permitAll().and()
				.formLogin().and().httpBasic().and().csrf().disable();

	}

}
