#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "${package}.repositories" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })

public class DataConfig {

	@Bean
	@Profile(value = { "devEmbed" })
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:sql/dev/devScheme.sql").build();

	}

	@Bean
	@Profile(value = { "devServerMySQL" })
	public DataSource dataSourceSQL() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/${artifactId}${artifactId}");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setInitialSize(50);
		dataSource.setMaxTotal(75);
		return dataSource;
	}

	@Bean
	@Profile(value = { "devServerMySQL" })
	public LocalSessionFactoryBean sessionFactoryMySQL(DataSource dataSourceSQL) {
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(dataSourceSQL);
		sfb.setPackagesToScan(new String[] { "${package}.domain" });
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		sfb.setHibernateProperties(props);
		return sfb;

	}

	@Bean
	@Profile(value = { "devEmbed" })
	public LocalSessionFactoryBean sessionFactoryEmbedded(DataSource dataSource) {
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setPackagesToScan(new String[] { "${package}.domain" });
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		sfb.setHibernateProperties(props);
		return sfb;

	}

}
