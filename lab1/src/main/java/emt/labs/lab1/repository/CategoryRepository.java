package emt.labs.lab1.repository;

import emt.labs.lab1.models.Category;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    private Long counter=0L;
    private List<Category> categories=null;
    @PostConstruct
    public void init()
    {
        Category c=new Category();
        c.setId(getNextIdCat());
        c.setName("Men");
        categories=new ArrayList<>();
        categories.add(c);
        Category c1=new Category();
        c1.setId(getNextIdCat());
        c1.setName("Women");
        categories.add(c1);
        Category c2=new Category();
        c2.setId(getNextIdCat());
        c2.setName("Kids");
        categories.add(c2);

    }
    public List<Category> findAllCategories()
    {
        return categories;
    }
    public Long getNextIdCat()
    {
        return counter++;
    }
}
