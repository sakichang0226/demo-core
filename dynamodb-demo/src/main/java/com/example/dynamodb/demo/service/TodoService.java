package com.example.dynamodb.demo.service;

import com.example.dynamodb.demo.constant.TableName;
import com.example.dynamodb.demo.model.Todo;
import com.example.dynamodb.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public Todo getTodo(String partitionKey, String seqUserId) {
        return repository.getItem(TableName.TODO, AttributeValue.fromS(partitionKey),
                AttributeValue.fromN(seqUserId));
    }

}
