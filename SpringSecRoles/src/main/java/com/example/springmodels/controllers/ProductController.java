package com.example.springmodels.controllers;


import com.example.springmodels.models.Person;
import com.example.springmodels.models.Product;
import com.example.springmodels.repos.PersonRepo;
import com.example.springmodels.repos.ProductRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('USER, DIRECTOR')")
public class ProductController {

    private ProductRepo us;
    private final PersonRepo pr;

    public List<Product> products = new ArrayList<>();
    public List<Person> per = new ArrayList<>();

    public ProductController(ProductRepo us, PersonRepo pr) {
        this.us = us;
        this.pr=pr;

    }

    @GetMapping("/Products")
    public String Product(Model model){
        products = us.findAll();

        model.addAttribute("products", products);
        per = pr.findAll();
        model.addAttribute("per", per);
        return "Products";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String DeleteProduct(@PathVariable("productId") int id) {

        us.deleteById(id);
        return "redirect:/Products";
    }

    @GetMapping("/UpdateProduct/{productId}")
    public String UpdateProduct(@PathVariable("productId") int id, Model model) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                model.addAttribute("name1", products.get(i).getName());
                model.addAttribute("id1", products.get(i).getId());
                model.addAttribute("quantity1", products.get(i).getQuantity());
                model.addAttribute("article1", products.get(i).getArticle());
                model.addAttribute("price1", products.get(i).getPrice());
                model.addAttribute("owner1", per.get(i).getSecondName());

            }
        }
        return "Product";
    }

    @PostMapping("/AddProduct")
    public String AddProduct(@ModelAttribute("name") String name, @ModelAttribute("article") String article, @ModelAttribute("owner") int owner, @ModelAttribute("quantity") Integer quantity, @ModelAttribute("price") Integer price){

            Person pers = pr.findById(owner).orElseThrow();
            us.save(new Product(name, article, pers, quantity, price));


        return "redirect:/Products";
    }

    @PostMapping("/updateProduct")
    public String UpdateProduct(@ModelAttribute("name1") String name, @ModelAttribute("article1") String article, @ModelAttribute("owner1") int owner, @ModelAttribute("quantity1") Integer quantity, @ModelAttribute("price1") Integer price, @ModelAttribute("id1") Integer id){
        Person pers = pr.findById(owner).orElseThrow();
        us.save(new Product(name, article, pers, quantity, price, id));
        return "redirect:/Products";
    }

    @GetMapping("/GetProduct")
    public String UpdateProduct(@ModelAttribute("article") String article, Model model) {
        Product product;
        product = us.findProductByArticle(article.trim());

        model.addAttribute("name1", product.getName());
        model.addAttribute("id1", product.getId());
        model.addAttribute("quantity1", product.getQuantity());
        model.addAttribute("article1", product.getArticle());
        model.addAttribute("price1", product.getPrice());
        model.addAttribute("owner1", product.getOwner().getSecondName());

        return "Product";
    }

    @GetMapping("/goIndex")
    public String Index() {

        return "index";
    }
}
