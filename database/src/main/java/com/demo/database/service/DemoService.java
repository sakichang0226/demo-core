package com.demo.database.service;

import com.demo.database.model.Demo;
import com.demo.database.repository.DemoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final DemoRepository repository;

    public Optional<Demo> get(@NonNull long id) {
        return repository.findById(id);
    }

}
