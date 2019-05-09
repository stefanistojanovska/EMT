package emt.labs.lab1.repository.jpa;

import emt.labs.lab1.models.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductInterface extends JpaRepository<Product,Long> {
 /*   Product save(Product p);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"manufacturer"})
    List<Product> findAll();

   *//* List<Product> findAllByCategoryId(Long id);*//*

    List<Product> findByCategoryIdAndManufacturerId(Long categoryId, Long manufacturerId);

    long count();

    long countByCategoryId(Long categoryId);

    List<Product> findByCategoryIdOrManufacturerId(Long categoryId, Long manufacturerId);

    List<Product> findByManufacturerNameLike(String mName);



    List<Product> findByManufacturerId(Long manId);*/
}
