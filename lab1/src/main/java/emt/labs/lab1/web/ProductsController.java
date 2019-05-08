package emt.labs.lab1.web;

import emt.labs.lab1.exceptions.CategoryNotFoundException;
import emt.labs.lab1.exceptions.ManufacturerNotFoundException;
import emt.labs.lab1.exceptions.ProductNotFoundException;
import emt.labs.lab1.models.Manufacturer;
import emt.labs.lab1.models.Product;
import emt.labs.lab1.models.Category;

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
        private List<Product> products=null;
        private List<Category> categories=null;
        private List<Manufacturer> manufacturers=null;




        @ExceptionHandler({ProductNotFoundException.class})
        @GetMapping ("product/{prid}")
        public String product(@PathVariable("prid") String prid, Model model)
        {

            Optional<Product> tmp=products.stream().filter(v ->{ return v.getId().toString().equals(prid.toString());} ).findAny();
            if(!tmp.isPresent())
                throw  new ProductNotFoundException();
            model.addAttribute("prid",prid);
            model.addAttribute("products",products);
            return "product";
        }

        @RequestMapping(value="products",method ={RequestMethod.GET})
        public String AllProducts(Model model)
        {
            model.addAttribute("products",products);
            model.addAttribute("categories",categories);
            model.addAttribute("manufacturers",manufacturers);

            return "products";
        }

    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    @RequestMapping(value="products/add",method ={RequestMethod.POST})
    public String addProductWithModel(@ModelAttribute Product p,Model model) {

        String name=p.getName();
        String description=p.getDescription();
        String link=p.getLink();
        Long manId = p.getManufacturer().getId();
        Optional<Manufacturer> man = manufacturers.stream().filter(v->{
            return v.getId().equals(manId);
        }).findAny();
        if (!man.isPresent()) {
            throw new ManufacturerNotFoundException();
        }
        //Long catId = Long.parseLong(req.getParameter("categoryId"));
        Long catId=p.getCategory().getId();
        Optional<Category> cat = categories.stream().filter(v->{
            return v.getId().equals(catId);
        }).findAny();
        if (!cat.isPresent()) {
            throw new CategoryNotFoundException();
        }
        Product newProduct=  new Product();
        newProduct.setId(p.getId());
        newProduct.setName(name);
        newProduct.setLink(link);
        newProduct.setDescription(description);
        newProduct.setManufacturer(man.get());
        newProduct.setCategory(cat.get());

        products.add(newProduct);
        model.addAttribute("products",products);
        model.addAttribute("manufacturer",manufacturers);
        model.addAttribute("categories",categories);
        return "redirect:/products";
    }

    @GetMapping("products/add")
    public String products(Model model) {
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("categories",categories);
        model.addAttribute("product", new Product());
        return "NewProductForm";
    }



    }

