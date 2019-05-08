package emt.labs.lab1.repository;

import emt.labs.lab1.models.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private Long counter=0L;
    private List<Product> products=null;

    @PostConstruct
    public void init()
    {
        counter=1L;
        products=new ArrayList<>();
        Product p1=new Product();
        p1.setId(getNextIdPr());
        p1.setName("NIKE FLYKNIT MAX");
        p1.setDescription("Running sneakers, Spring 2016");
        p1.setLink("https://www.sportvision.mk/files/thumbs/files/images/slike_proizvoda/thumbs_320/620469-008_320_320px.jpg");
        p1.setPrice(6000D);
        products.add(p1);
    }
    public List<Product> findAllProducts()
    {
        return products;
    }
    public Product save(Product product)
    {
        product.setId(getNextIdPr());
        products.add(product);
        return product;
    }
    public void delete(Long productId)
    {
        products.removeIf(v->
        {
            return v.getId().equals(productId);
        });
    }

    public Optional<Product> findById(Long productId)
    {
        return products.stream().filter(v->v.getId().equals(productId)).findAny();


    }

    public Long getNextIdPr() {
        return counter++;
    }

}
