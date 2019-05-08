package emt.labs.lab1.repository.jpa;

import emt.labs.lab1.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaManufacturerInterface extends JpaRepository<Manufacturer,Long> {
}
