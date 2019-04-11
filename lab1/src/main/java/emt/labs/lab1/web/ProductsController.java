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

        private Long prCounter=1l;
        private Long catCounter=1l;
        private Long manCounter=1l;

        @PostConstruct
        public void init()
        {
            manufacturers=new ArrayList<>();
            products=new ArrayList<>();
            categories=new ArrayList<>();
            //POLNENJE LISTI
            Manufacturer m1=new Manufacturer();
            m1.setId(getNextIdMan());
            m1.setName("Nike");
            Manufacturer m2=new Manufacturer();
            m2.setName("Adidas");
            m2.setId(getNextIdMan());
            manufacturers.add(m1);
            manufacturers.add(m2);
            Category c1=new Category();
            Category c2=new Category();
            Category c3=new Category();
            c1.setId(getNextIdCat());
            c2.setId(getNextIdCat());
            c3.setId(getNextIdCat());
            c1.setName("Women");
            c2.setName("Men");
            c3.setName("Kids");
            categories.add(c1);
            categories.add(c2);
            categories.add(c3);
            Product p1=new Product();
            p1.setCategory(c2);
            p1.setId(getNextIdPr());
            p1.setManufacturer(m1);
            p1.setName("NIKE FLYKNIT MAX");
            p1.setDescription("Running sneakers, Spring 2016");
            p1.setLink("https://www.sportvision.mk/files/thumbs/files/images/slike_proizvoda/thumbs_320/620469-008_320_320px.jpg");
            products.add(p1);

        }

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
        //String name=req.getParameter("name");
        String name=p.getName();
        //String description=req.getParameter("description");
        String description=p.getDescription();
        //String link=req.getParameter("link");
        String link=p.getLink();
        //Long manId = Long.parseLong(req.getParameter("manufacturerId"));
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
        newProduct.setId(getNextIdPr());
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
        private Long getNextIdCat() {
            return catCounter++;
        }
        private Long getNextIdMan() {
            return manCounter++;
        }
        private Long getNextIdPr() {
            return prCounter++;
        }

    }

