package emt.labs.lab1.service.impl;

import emt.labs.lab1.models.Category;
import emt.labs.lab1.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Optional<Category> getOne(Long id) {
        return Optional.empty();
    }
}
