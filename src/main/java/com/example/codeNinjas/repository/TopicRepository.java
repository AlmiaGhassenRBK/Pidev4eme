package com.example.codeNinjas.repository;

import com.example.codeNinjas.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    Optional<Topic> findByName(String TopicName);
}
