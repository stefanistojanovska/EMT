package emt.labs.lab1.service;

import emt.labs.lab1.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    Optional<Category> getOne(Long id);

}
