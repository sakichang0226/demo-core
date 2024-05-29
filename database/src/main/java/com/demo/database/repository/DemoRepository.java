package com.demo.database.repository;

import com.demo.database.model.Demo;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface DemoRepository {

    public Optional<Demo> findById(long id);

}
