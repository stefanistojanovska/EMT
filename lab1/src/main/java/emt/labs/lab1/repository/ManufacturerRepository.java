package emt.labs.lab1.repository;

import emt.labs.lab1.models.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ManufacturerRepository {
    private Long counter = 0L;
    private List<Manufacturer> manufacturers = null;

    @PostConstruct
    public void postConstruct() {

        manufacturers = new ArrayList<>();
        Manufacturer m = new Manufacturer();
        m.setId(getNextIdMan());
        m.setName("Nike");
        manufacturers.add(m);
        Manufacturer m1=new Manufacturer();
        m.setId(getNextIdMan());
        m.setName("Adidas");
        manufacturers.add(m1);

    }

    public List<Manufacturer> findAllManufacturers() {
        return manufacturers;
    }

    private Long getNextIdMan() {
        return counter++;
    }
}
