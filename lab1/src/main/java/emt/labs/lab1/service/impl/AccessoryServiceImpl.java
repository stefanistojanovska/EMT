package emt.labs.lab1.service.impl;

import emt.labs.lab1.models.Accessory;
import emt.labs.lab1.service.AccessoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessoryServiceImpl implements AccessoryService {
    @Override
    public List<Accessory> getAll() {
        return null;
    }

    @Override
    public Optional<Accessory> getOne(Long id) {
        return Optional.empty();
    }
}
