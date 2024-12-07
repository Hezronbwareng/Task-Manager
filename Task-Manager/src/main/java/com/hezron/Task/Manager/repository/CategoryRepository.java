package com.hezron.Task.Manager.repository;

import com.hezron.Task.Manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName (String name);
}
