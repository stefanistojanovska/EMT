package emt.labs.lab1.repository.jpa;

import emt.labs.lab1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryInterface extends JpaRepository<Category,Long> {
}
