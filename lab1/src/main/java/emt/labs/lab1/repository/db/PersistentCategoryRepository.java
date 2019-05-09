package emt.labs.lab1.repository.db;

import emt.labs.lab1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersistentCategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAll();
}
