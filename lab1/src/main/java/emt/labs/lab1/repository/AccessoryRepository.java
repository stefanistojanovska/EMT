package emt.labs.lab1.repository;

import emt.labs.lab1.models.Accessory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccessoryRepository {
    private Long counter=0L;
    private List<Accessory> accessories=null;

    @PostConstruct
    public void init()
    {

        Accessory acc=new Accessory();
        acc.setId(getNextIdAcc());
        acc.setName("Headphones");
        acc.setDesc("Wireless Noise Cancelling Headphones");
        accessories=new ArrayList<>();
        accessories.add(acc);
        Accessory acc1= new Accessory();
        acc1.setId(getNextIdAcc());
        acc1.setName("Charger");
        acc1.setDesc("Adaptive Fast Charging (AFC) technology for AFC compatible devices");
        accessories.add(acc1);
    }
    public List<Accessory> findAllAccessories(){return accessories;}
    public Long getNextIdAcc()
    {
        return counter++;
    }
}
