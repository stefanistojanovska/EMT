package emt.labs.lab1.service.impl;

import emt.labs.lab1.models.Category;
import emt.labs.lab1.repository.db.PersistentCategoryRepository;
import emt.labs.lab1.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private PersistentCategoryRepository repo;
    public CategoryServiceImpl(PersistentCategoryRepository repo)
    {
        this.repo=repo;
    }
    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Category> getOne(Long id) {
        return Optional.empty();
    }
}
