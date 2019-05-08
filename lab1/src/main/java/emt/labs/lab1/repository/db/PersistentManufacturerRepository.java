package emt.labs.lab1.repository.db;

import emt.labs.lab1.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersistentManufacturerRepository extends JpaRepository<Manufacturer,Long> {
    List<Manufacturer> findAll();

    @Query("select m from Manufacturer m where m.id=:id")
    Optional<Manufacturer> getById(@Param("id") Long id);
}
