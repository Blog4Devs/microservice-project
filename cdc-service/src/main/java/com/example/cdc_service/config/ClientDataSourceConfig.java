package com.example.cdc_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.repositories.client",
    entityManagerFactoryRef = "clientEntityManagerFactory",
    transactionManagerRef = "clientTransactionManager"
)
public class ClientDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.client")
    public DataSource clientDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean clientEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(clientDataSource())
                .packages("com.example.entities.client")
                .persistenceUnit("client")
                .build();
    }

    @Bean
    public PlatformTransactionManager clientTransactionManager(
            @Qualifier("clientEntityManagerFactory") EntityManagerFactory clientEntityManagerFactory) {
        return new JpaTransactionManager(clientEntityManagerFactory);
    }
}
