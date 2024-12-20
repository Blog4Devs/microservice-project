package com.example.cdc_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.cdc_service.repositories.invoice",
        mongoTemplateRef = "invoiceMongoTemplate"
)
@PropertySource({ "classpath:application.properties" })
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongodb.database");
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(env.getProperty("mongodb.uri"));
    }

    @Primary
    @Bean(name = "invoiceMongoTemplate")
    public MongoTemplate invoiceMongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    @Primary
    @Bean
    public MappingMongoConverter mongoConverter(
            MongoDatabaseFactory mongoFactory,
            MongoMappingContext mongoMappingContext
    ) {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setMapKeyDotReplacement("_");
        return converter;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}