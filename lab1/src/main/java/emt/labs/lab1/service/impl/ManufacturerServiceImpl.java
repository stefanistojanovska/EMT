package emt.labs.lab1.service.impl;

import emt.labs.lab1.models.Manufacturer;
import emt.labs.lab1.repository.ManufacturerRepository;
import emt.labs.lab1.repository.db.PersistentManufacturerRepository;
import emt.labs.lab1.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private PersistentManufacturerRepository repo;
    public ManufacturerServiceImpl(PersistentManufacturerRepository repo)
    {
        this.repo=repo;
    }
    @Override
    public List<Manufacturer> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Manufacturer> getOne(Long id) {
        return Optional.empty();
    }
}
