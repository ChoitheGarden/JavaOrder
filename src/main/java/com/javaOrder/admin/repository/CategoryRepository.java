package com.javaOrder.admin.repository;

import com.javaOrder.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("SELECT MAX(c.code) FROM Category c WHERE c.code LIKE :prefix%")
    String findMaxCategoryCodeByPrefix(@Param("prefix") String prefix);
}
