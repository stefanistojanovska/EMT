package emt.labs.lab1.repository.db;

import emt.labs.lab1.models.Manufacturer;
import emt.labs.lab1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersistentProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAll();
    Optional<Product> getById(@Param("id") Long id);
    //@Query("select p from Product p where p.categoryId=:categoryId")
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByCategoryIdAndManufacturerId(Long categoryId,Long manufacturerId);
   /* @Query("select sum(price) from product p where p.categoryId=:categoryId ")
    Long findByCategoryIdAndCalculatePrice(Long categoryId);*/
   Product save(Product p);

}
