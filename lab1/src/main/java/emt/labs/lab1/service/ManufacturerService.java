package emt.labs.lab1.service;

import emt.labs.lab1.models.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> getAll();
    Optional<Manufacturer> getOne(Long id);

}
