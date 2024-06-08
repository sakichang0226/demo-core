package com.example.dynamodb.demo.repository;

import com.example.dynamodb.demo.model.Todo;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class TodoRepository extends AbstractDynamoDBRepository<Todo>{
    public TodoRepository(DynamoDbEnhancedClient client) {
        super(Todo.class, client);
    }
}
