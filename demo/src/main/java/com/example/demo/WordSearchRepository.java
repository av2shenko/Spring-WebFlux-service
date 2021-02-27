package com.example.demo;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WordSearchRepository extends ReactiveCrudRepository<WordSearch, Long> {
}