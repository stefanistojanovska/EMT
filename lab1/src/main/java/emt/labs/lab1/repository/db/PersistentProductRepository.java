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
    @Query("select p from Product p where p.id=:id")
    Optional<Product> getById(@Param("id") Long id);
    //@Query("select p from Product p where p.categoryId=:categoryId")
    //List<Product> findAllByCategoryId(Long categoryId);
}
