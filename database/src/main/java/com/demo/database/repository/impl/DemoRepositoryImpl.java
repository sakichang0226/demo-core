package com.demo.database.repository.impl;

import com.demo.database.mapper.DemoMapper;
import com.demo.database.model.Demo;
import com.demo.database.repository.DemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DemoRepositoryImpl implements DemoRepository {

    private final DemoMapper mapper;

    @Override
    public Optional<Demo> findById(long id) {
        return Optional.of(mapper.selectByPrimaryKey(id));
    }

}
