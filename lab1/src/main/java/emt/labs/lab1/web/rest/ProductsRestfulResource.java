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

    public ProductsRestfulResource(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("products/{pr_id}")
    public Product findProduct(@PathVariable("pr_id") Long id) {
        Product p = productService.getOne(id).orElseThrow(() -> new ProductNotFoundException());
        return p;
    }
   @GetMapping("product/category/{cat_id}")
    public List<Product> findProductsByCategory(@PathVariable("cat_id") Long id) {        //return productService.findAllByCategoryId(id);
        return productService.findByCategoryId(id);
    }
    @GetMapping("product/category/{catId}/manufacturer/{manId}")
    public List<Product> findProductsByCategoryAndManufacturer(@PathVariable("catId")Long cId,@PathVariable("manId")Long mId)
    {
        return productService.findByCategoryIdAndManufacturerId(cId, mId);
    }
    @GetMapping("product/category/{categoryId}/price")
    public Double calculatePriceByCategory(@PathVariable("categoryId")Long id)
    {
        Double sum=0D;
       List<Product>products= productService.findByCategoryId(id);
        for (Product p:products) {
            sum+=p.getPrice();
        }
        return sum;
    }
}
