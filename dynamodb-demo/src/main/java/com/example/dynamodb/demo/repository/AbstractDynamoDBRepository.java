package com.example.dynamodb.demo.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RequiredArgsConstructor
public abstract class AbstractDynamoDBRepository<T> {

    @Getter
    private final Class<T> type;

    private final DynamoDbEnhancedClient client;

    public T getItem(String tableName, AttributeValue partitionKey) {

        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(type));
        Key key = Key.builder().partitionValue(partitionKey).build();

        return table.getItem(GetItemEnhancedRequest.builder().key(key).build());
    }

    public T getItem(String tableName, AttributeValue partitionKey, AttributeValue sortKey) {

        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(type));
        Key key = Key.builder().partitionValue(partitionKey).sortValue(sortKey).build();

        return table.getItem(GetItemEnhancedRequest.builder().key(key).build());
    }

}
