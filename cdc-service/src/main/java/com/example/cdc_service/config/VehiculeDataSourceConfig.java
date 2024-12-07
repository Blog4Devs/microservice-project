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
    basePackages = "com.example.cdc_service.entities.vehicule",
    entityManagerFactoryRef = "vehicleEntityManagerFactory",
    transactionManagerRef = "vehicleTransactionManager"
)
public class VehiculeDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.vehicule")
    public DataSource vehiculeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean vehiculeEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(vehiculeDataSource())
                .packages("com.example.cdc_service.entities.maintenance")
                .persistenceUnit("vehicule")
                .build();
    }

    @Bean
    public PlatformTransactionManager vehiculeTransactionManager(
            @Qualifier("vehiculeEntityManagerFactory") EntityManagerFactory vehiculeEntityManagerFactory) {
        return new JpaTransactionManager(vehiculeEntityManagerFactory);
    }
}
