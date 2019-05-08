package emt.labs.lab1.web.rest;

import emt.labs.lab1.exceptions.ProductNotFoundException;
import emt.labs.lab1.models.Manufacturer;
import emt.labs.lab1.models.Product;
import emt.labs.lab1.service.CategoryService;
import emt.labs.lab1.service.ManufacturerService;
import emt.labs.lab1.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/")
public class ProductsRestfulResource {
    private ProductService productService;
    private CategoryService categoryService;
    //private ManufacturerService manufacturerService;
     public ProductsRestfulResource( ProductService productService,CategoryService categoryService)
     {
        this.categoryService=categoryService;
         this.productService=productService;
     }
    @GetMapping("products")
    public List<Product> getAllProducts(){return productService.getAllProducts();}

/*    @GetMapping("mans")
    public List<Manufacturer> getAllManufacturers(){
        return manufacturerService.getAll();
    }*/
    @GetMapping("products/{pr_id}")
    public Product findProduct(@PathVariable("pr_id") Long id)
    {
        Product p=productService.getOne(id).orElseThrow(()->new ProductNotFoundException());
        return p;
    }
    @GetMapping("category/{cat_id}")
    public List<Product> findProductsByCategory(@PathVariable("cat_id") Long id)
    {
         //return productService.findAllByCategoryId(id);
        return null;
    }
}
