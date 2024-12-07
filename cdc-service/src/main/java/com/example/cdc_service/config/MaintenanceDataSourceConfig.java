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
    basePackages = "com.example.cdc_service.entities.maintenance",
    entityManagerFactoryRef = "maintenanceEntityManagerFactory",
    transactionManagerRef = "maintenanceTransactionManager"
)
public class MaintenanceDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.maintenance")
    public DataSource maintenanceDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean maintenanceEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(maintenanceDataSource())
                .packages("com.example.cdc_service.entities.maintenance")
                .persistenceUnit("maintenance")
                .build();
    }

    @Bean
    public PlatformTransactionManager maintenanceTransactionManager(
            @Qualifier("maintenanceEntityManagerFactory") EntityManagerFactory maintenanceEntityManagerFactory) {
        return new JpaTransactionManager(maintenanceEntityManagerFactory);
    }
}
