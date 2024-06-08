package com.example.dynamodb.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDBConfiguration {

    @Value("${dynamo.endpoint}")
    String endpoint;

   @Bean
   public DynamoDbClient dynamoDbClient() {
       return DynamoDbClient.builder()
               .endpointOverride(URI.create(endpoint))
               .build();
   }

   @Bean
   public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient client) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client).build();
   }

}
