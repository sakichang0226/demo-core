package com.example.dynamodb.demo.repository;

import com.example.dynamodb.demo.constant.DynamoDBError;
import com.example.dynamodb.demo.exception.DynamoDbDemoBusinessException;
import com.example.dynamodb.demo.exception.DynamoDbDemoSystemException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractDynamoDBRepository<T> {

    @Getter
    private final Class<T> type;

    private final DynamoDbEnhancedClient client;

    public T getItem(String tableName, AttributeValue partitionKey) {

        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(type));
        Key key = Key.builder().partitionValue(partitionKey).build();
        T item;

        try {
            item = table.getItem(GetItemEnhancedRequest.builder().key(key).build());
        } catch (DynamoDbException e) {
            throw new DynamoDbDemoSystemException(
                    DynamoDBError.DYNAMO_DB_ERROR001.getCode() + ":" + DynamoDBError.DYNAMO_DB_ERROR001.getMessage(),
                    e);
        }

        if (item == null) {
            throw new DynamoDbDemoBusinessException(DynamoDBError.DYNAMO_DB_ERROR002.getCode() + ":" +
                    DynamoDBError.DYNAMO_DB_ERROR002.getMessage());
        }

        return item;
    }

    public T getItem(String tableName, AttributeValue partitionKey, AttributeValue sortKey) {

        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(type));
        Key key = Key.builder().partitionValue(partitionKey).sortValue(sortKey).build();
        T item;

        try {
            item = table.getItem(GetItemEnhancedRequest.builder().key(key).build());
        } catch (DynamoDbException e) {
            throw new DynamoDbDemoSystemException(
                    DynamoDBError.DYNAMO_DB_ERROR001.getCode() + ":" + DynamoDBError.DYNAMO_DB_ERROR001.getMessage(),
                    e);
        }

        if (item == null) {
            throw new DynamoDbDemoBusinessException(DynamoDBError.DYNAMO_DB_ERROR002.getCode() + ":" +
                    DynamoDBError.DYNAMO_DB_ERROR002.getMessage());
        }

        return item;
    }

    public List<T> getItems(String tableName, AttributeValue key) {
        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(type));
        Iterator<T> results;

        try {
            results = table.query(QueryConditional.keyEqualTo(k -> k.partitionValue(key))).items().iterator();
        } catch (DynamoDbException e) {
            throw new DynamoDbDemoSystemException(
                    DynamoDBError.DYNAMO_DB_ERROR001.getCode() + ":" + DynamoDBError.DYNAMO_DB_ERROR001.getMessage(),
                    e);
        }

        List<T> list = new ArrayList<>();
        results.forEachRemaining(list::add);

        return list;
    }

}
