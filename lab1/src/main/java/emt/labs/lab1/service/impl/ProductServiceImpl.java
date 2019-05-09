package emt.labs.lab1.service.impl;

import emt.labs.lab1.exceptions.ManufacturerNotFoundException;
import emt.labs.lab1.exceptions.ProductNotFoundException;
import emt.labs.lab1.models.Product;
import emt.labs.lab1.repository.ProductRepository;
import emt.labs.lab1.repository.db.PersistentManufacturerRepository;
import emt.labs.lab1.repository.db.PersistentProductRepository;
import emt.labs.lab1.service.ManufacturerService;
import emt.labs.lab1.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    private PersistentProductRepository repo;


    public ProductServiceImpl(PersistentProductRepository repo)
    {
        this.repo=repo;
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public List<Product> findByCategoryId(Long catId) {
        return repo.findByCategoryId(catId);
    }

    @Override
    public List<Product> findByCategoryIdAndManufacturerId(Long categoryId,Long manufacturerId)
    {
        return repo.findByCategoryIdAndManufacturerId(categoryId,manufacturerId);
    }

    @Override
    public Optional<Product> getOne(Long id) {
        return getAllProducts().stream().filter(v->id.equals(v.getId())).findAny();
    }

    @Override
    public Product addNewProduct(Product product) {
        return repo.save(product);
    }





    @Override
    public Product addNewProduct(Product product, Long manufacturerId) {
        return null;
    }

    @Override
    public Product addNewDevice(String name, Double price, Long manufacturerId) throws ManufacturerNotFoundException {
        return null;
    }




    @Override
    public Product update(Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void delete(Long productId) {

    }




   /* @Override
    public Product getById(Long productId) {
        return null;
    }*/

    @Override
    public List<Product> getByManId(Long manId) {
        return null;
    }
}
