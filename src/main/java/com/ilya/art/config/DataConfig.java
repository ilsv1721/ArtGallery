package com.ilya.art.config;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "com.ilya.art.repositories" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@EnableTransactionManagement
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
		Properties props = new Properties();
		try {
			props.load(this.getClass().getResourceAsStream("/database.properties"));
			dataSource.setDriverClassName(props.getProperty("db.driver"));
			dataSource.setUrl(props.getProperty("db.url"));
			dataSource.setUsername(props.getProperty("db.user"));
			dataSource.setPassword(props.getProperty("db.pass"));
			dataSource.setInitialSize(Integer.parseInt(props.getProperty("db.InitSize")));
			dataSource.setMaxTotal(Integer.parseInt(props.getProperty("db.MaxActive")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	@Bean
	@Profile(value = { "devServerMySQL" })
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}

	/*
	 * @Bean
	 * 
	 * @Profile(value = { "devServerMySQL" }) public LocalSessionFactoryBean
	 * sessionFactoryMySQL(DataSource dataSourceSQL) { LocalSessionFactoryBean
	 * sfb = new LocalSessionFactoryBean(); sfb.setDataSource(dataSourceSQL);
	 * sfb.setPackagesToScan(new String[] { "com.ilya.art.domain" }); Properties
	 * props = new Properties(); props.setProperty("hibernate.dialect",
	 * "org.hibernate.dialect.MySQLDialect"); sfb.setHibernateProperties(props);
	 * return sfb;
	 * 
	 * }
	 * 
	 * @Bean
	 * 
	 * @Profile(value = { "devEmbed" }) public LocalSessionFactoryBean
	 * sessionFactoryEmbedded(DataSource dataSource) { LocalSessionFactoryBean
	 * sfb = new LocalSessionFactoryBean(); sfb.setDataSource(dataSource);
	 * sfb.setPackagesToScan(new String[] { "com.ilya.art.domain" }); Properties
	 * props = new Properties(); props.setProperty("hibernate.dialect",
	 * "org.hibernate.dialect.H2Dialect"); sfb.setHibernateProperties(props);
	 * return sfb;
	 * 
	 * }
	 * 
	 */

	@Bean
	public PlatformTransactionManager txManager(EntityManagerFactory emf, DataSource dataSource) {
		JpaTransactionManager jpaTxManager = new JpaTransactionManager();
		jpaTxManager.setDataSource(dataSource);
		jpaTxManager.setEntityManagerFactory(emf);
		return jpaTxManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("com.ilya.art.domain");

		return emfb;
	}
}
