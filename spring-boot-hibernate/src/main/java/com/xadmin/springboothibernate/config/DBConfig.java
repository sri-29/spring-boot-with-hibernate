package com.xadmin.springboothibernate.config;
import java.util.Properties;

import javax.activation.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class DBConfig {
@Value("${jdbc.driverClassName}")
private String driverClass;
@Value("${jdbc.url}")
private String url;
@Value("${jdbc.username}")
private String username;
@Value("${jdbc.password}")
private String password;
@Value("${jdbc.dialect}")
private String dialect;

@Bean
public DriverManagerDataSource getDatasource()
{
	DriverManagerDataSource datasource = new DriverManagerDataSource(url, username, password);
	datasource.setDriverClassName(driverClass);
	return  datasource;
}
private Properties hibernateProperties() {
	Properties pr = new Properties();
	pr.put("hibernate.dialect",dialect);
pr.put("hibernate.hbm2ddl.auto", "update");
pr.put("hibernate.show_sql", "true");
pr.put("hibernate.show_sql", "true");
return pr;		
}
@Bean
public LocalSessionFactoryBean sessionFactory()
{
	LocalSessionFactoryBean fac = new LocalSessionFactoryBean();
	fac.setDataSource(getDatasource());
	fac.setHibernateProperties(hibernateProperties());
	fac.setPackagesToScan(new String[] {"com.xadmin.springboothibernate.model"});
	return fac;
}
@Bean
@Autowired
public HibernateTransactionManager transactionManager(SessionFactory factory)
{
	HibernateTransactionManager tra = new HibernateTransactionManager();
	tra.setSessionFactory(factory);
	return tra;
}
}


