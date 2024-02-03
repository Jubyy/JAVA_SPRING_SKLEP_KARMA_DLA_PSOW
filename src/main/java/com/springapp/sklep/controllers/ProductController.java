package com.springapp.sklep.controllers;

import com.springapp.sklep.domain.Product;
import com.springapp.sklep.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public String productList(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/products");
        return "layout";
    }
    @GetMapping("/")
    public String index(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/index");
        return "layout";
    }
    @GetMapping("/kontakt")
    public String kontakt(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/kontakt");
        return "layout";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("contentTemplate", "fragments/createProduct");
        return "layout";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Iterable<Product> products = productRepository.findAll();
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        model.addAttribute("contentTemplate", "fragments/editProduct");
        return "layout";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}

