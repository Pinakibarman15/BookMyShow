package com.restoreBookshow.demo.dbconfig;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//import javax.persistence.EntityManagerFactory;
//import jakarta.s

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"com.lowes.crawling.repo", "com.restoreBookshow.demo.models", "com.lowes", "com.lowes.ci" })
@Slf4j
public class PostgreActiveConfig {

	@Value("${datasource.postgre-active.jdbcUrl}")
	private String jdbcUrl;

	@Value("${datasource.postgre-active.jdbcReadUrl}")
	private String jdbcReadUrl;

	@Value("${datasource.postgre-active.driverClassName}")
	private String driverClassName;

	@Value("${datasource.postgre-active.cacert}")
	private String activeRootCrt;

	@Value("${datasource.postgre-active.servercrt}")
	private String activeCert;

	@Value("${datasource.postgre-active.serverpk8}")
	private String activeKey;

	@Value("${datasource.postgre-active.creds}")
	private String credsPath;
	
	@Value("${datasource.postgre-active.poolSize: 5}")
	private int poolSize;
	
	@Value("${datasource.postgre-active.maxLifeTime: 200000}")
	private int maxLifeTime;
	
	@Value("${datasource.postgre-active.connectionTimeout: 640000}")
	private int connectionTimeout;

	private ObjectMapper mapper = new ObjectMapper();

	@Bean(name = "postgressReadWriteDatasource")
	public DataSource postgressReadWriteDataSource() {
		HikariDataSource dataSource = getDataSource(jdbcUrl);
		return dataSource;
	}
	
	@Bean(name = "postgressReadDatasource")
	public DataSource postgressReadDataSource() {
		HikariDataSource dataSource = getDataSource(jdbcReadUrl);
		return dataSource;
	}

	private HikariDataSource getDataSource(String jdbcUrlByReplica) {
		Properties properties = new Properties();
		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(jdbcUrlByReplica);
		dataSource.setMaximumPoolSize(poolSize);
		dataSource.setMaxLifetime(maxLifeTime);
		dataSource.setConnectionTimeout(connectionTimeout);
		Credentials creds = null;
		try {
			Reader postgreSqlReader = Files.newBufferedReader(Paths.get(credsPath));
			creds = mapper.readValue(postgreSqlReader, Credentials.class);
		} catch (IOException e) {
			log.error("Error occured while reading credsPath with error {}", e);
		}

		if (creds != null) {
			dataSource.setUsername(creds.getUsername());
			dataSource.setPassword(creds.getPassword());
		}

		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
		properties.setProperty(Constants.Db.SSL, Constants.Db.TRUE);
		properties.setProperty(Constants.Db.SSL_MODE, Constants.Db.VERIFY_CA);
		properties.setProperty(Constants.Db.SSL_ROOT_CERT, activeRootCrt);
		properties.setProperty(Constants.Db.SSL_KEY, activeKey);
		properties.setProperty(Constants.Db.SSL_CERT, activeCert);

		dataSource.setDataSourceProperties(properties);
		return dataSource;
	}

	@Primary
	@Bean("postgressDatasource")
	public TransactionRoutingDataSource actualDataSource() {
		TransactionRoutingDataSource routingDataSource = new TransactionRoutingDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(DataSourceType.READ_WRITE, postgressReadWriteDataSource());
		dataSourceMap.put(DataSourceType.READ_ONLY, postgressReadDataSource());
		routingDataSource.setTargetDataSources(dataSourceMap);
		return routingDataSource;
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("postgressDatasource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.lowes.crawling.entity","com.lowes.entity","com.lowes.scraping.entity","com.restoreBookshow.demo.models").persistenceUnit("postgress")
				.build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
