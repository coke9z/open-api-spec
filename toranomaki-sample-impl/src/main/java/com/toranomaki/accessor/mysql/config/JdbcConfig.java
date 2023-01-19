package com.toranomaki.accessor.mysql.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * SpringJDBC Configuration.
 *
 * @author corin
 */
@Configuration
public class JdbcConfig {

  @Value("${mysql.datasource.url}")
  private String url;

  @Value("${mysql.datasource.username}")
  private String userName;

  @Value("${mysql.datasource.password}")
  private String password;

  @Bean
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
    driverManagerDataSource.setUrl(url);
    driverManagerDataSource.setUsername(userName);
    driverManagerDataSource.setPassword(password);
    return driverManagerDataSource;
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }
}
