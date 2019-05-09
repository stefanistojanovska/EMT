package emt.labs.lab1.service;

import emt.labs.lab1.exceptions.ManufacturerNotFoundException;
import emt.labs.lab1.exceptions.ProductNotFoundException;
import emt.labs.lab1.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getOne(Long id);
   List<Product> findByCategoryId(Long categoryId);
   List<Product> findByCategoryIdAndManufacturerId(Long categoryId,Long manufacturerId);

   Product addNewProduct(Product product, Long manufacturerId);
   Product addNewDevice(String name, Double price, Long manufacturerId) throws ManufacturerNotFoundException;

   Product addNewProduct(Product product);

    Product update(Product product) throws ProductNotFoundException;
    void delete(Long productId);



    List<Product> getByManId(Long manId);

}
