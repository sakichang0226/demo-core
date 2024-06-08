package com.example.dynamodb.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDBConfiguration {

    @Value("${amazon.dynamodb.endpoint:https://dynamodb.ap-northeast-1.amazonaws.com/}")
    private String endpoint;

   @Bean
   public DynamoDbClient dynamoDbClient(URI dynamoDbEndPoint) {
       return DynamoDbClient.builder()
               .endpointOverride(dynamoDbEndPoint)
               .build();
   }

   @Bean
   public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient client) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client).build();
   }

   @Bean
   public URI dynamoDbEndPoint() {
        return URI.create(endpoint);
   }

}
