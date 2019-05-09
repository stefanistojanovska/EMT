package emt.labs.lab1.web;

import emt.labs.lab1.exceptions.CategoryNotFoundException;
import emt.labs.lab1.exceptions.ManufacturerNotFoundException;
import emt.labs.lab1.exceptions.ProductNotFoundException;
import emt.labs.lab1.models.Manufacturer;
import emt.labs.lab1.models.Product;
import emt.labs.lab1.models.Category;

import emt.labs.lab1.service.CategoryService;
import emt.labs.lab1.service.ManufacturerService;
import emt.labs.lab1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
    @RequestMapping("/")
    public class ProductsController {
       private ProductService productService;
       private ManufacturerService manufacturerService;
       private CategoryService categoryService;

        public ProductsController(ProductService productService, ManufacturerService manufacturerService, CategoryService categoryService)
        {
            this.productService=productService;
            this.manufacturerService=manufacturerService;
            this.categoryService=categoryService;
        }



        @ExceptionHandler({ProductNotFoundException.class})
        @GetMapping ("product/{prid}")
        public String productInfo(@PathVariable("prid") Long prid,Model model)
        {

            Optional<Product> tmp=productService.getOne(prid);
            if(!tmp.isPresent())
                throw  new ProductNotFoundException();
            model.addAttribute("prid",prid);
            model.addAttribute("products",productService.getAllProducts());
            return "product";
        }

        @RequestMapping(value="products",method ={RequestMethod.GET})
        public String AllProducts(Model model)
        {
            model.addAttribute("products",productService.getAllProducts());
            model.addAttribute("categories",categoryService.getAll());
            model.addAttribute("manufacturers",manufacturerService.getAll());

            return "products";
        }

    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    @RequestMapping(value="products/add",method ={RequestMethod.POST})
    public String addProductWithModel(@ModelAttribute Product p,Model model) {

        String name=p.getName();
        String description=p.getDescription();
        String link=p.getLink();

        Optional<Manufacturer> man = manufacturerService.getOne(p.getManufacturer().getId());
        if (!man.isPresent()) {
            throw new ManufacturerNotFoundException();
        }
        //Long catId = Long.parseLong(req.getParameter("categoryId"));
        Long catId=p.getCategory().getId();
       // Optional<Category> cat = categoryService.getOne(p.getCategory().getId());
        Optional<Category> cat = categoryService.getOne(p.getCategory().getId());

        if (!cat.isPresent()) {
            throw new CategoryNotFoundException();
        }
        Product newProduct=  new Product();
        newProduct.setId(p.getId());
        newProduct.setName(name);
        newProduct.setLink(link);
        newProduct.setDescription(description);
        newProduct.setManufacturer(p.getManufacturer());
        newProduct.setCategory(p.getCategory());
        newProduct.setPrice(p.getPrice());

        productService.addNewProduct(newProduct);
        model.addAttribute("products",productService.getAllProducts());
        model.addAttribute("manufacturer",manufacturerService.getAll());
        model.addAttribute("categories",categoryService.getAll());
        return "redirect:/products";
    }

    @GetMapping("products/add")
    public String products(Model model) {
        model.addAttribute("manufacturers",manufacturerService.getAll());
        model.addAttribute("categories",categoryService.getAll());
        model.addAttribute("product", new Product());
        return "NewProductForm";
    }



    }

