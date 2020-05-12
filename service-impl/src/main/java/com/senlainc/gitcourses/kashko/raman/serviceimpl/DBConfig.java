package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:database.properties"})
@EnableTransactionManagement
@ComponentScan({"com.senlainc.gitcourses.kashko.raman.repositoryimpl",
        "com.senlainc.gitcourses.kashko.raman.serviceimpl",
"com.senlainc.gitcourses.kashko.raman.dto"})
public class DBConfig {
    @Value("${url}")
    private String dataBaseUrl;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${dialectHB}")
    private String dialect;
    @Value("${dialectMSQL}")
    private String dialectMSQL;
    @Value("${showSQL}")
    private String showSQL;
    @Value("${formatSQL}")
    private String formatSQL;
    public DBConfig() {

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfiguere() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(dataBaseUrl);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.senlainc.gitcourses.kashko.raman.entity");
        JpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(jpaAdapter);
        entityManagerFactory.setJpaProperties(additionalProp());

        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager txManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProp() {
        Properties properties = new Properties();
        properties.setProperty(dialect, dialectMSQL);
        properties.setProperty(showSQL, "true");
        properties.setProperty(formatSQL, "true");

        return properties;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
