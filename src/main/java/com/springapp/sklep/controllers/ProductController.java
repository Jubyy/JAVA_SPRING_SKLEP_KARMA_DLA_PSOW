package com.springapp.sklep.controllers;

import com.springapp.sklep.domain.Product;
import com.springapp.sklep.repositories.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/products")
    public String productList(Model model,Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }


        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/products");
        return "layout";
    }
    @GetMapping("/")
    public String index(Model model,Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/index");
        return "layout";
    }

    @PostMapping("/submit")
    public String submit(Model model,Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/submit");
        return "layout";
    }

    @GetMapping("/login")
    public String login(Model model) {

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/login");
        return "layout";
    }

    @GetMapping("/kontakt")
    public String kontakt(Model model,Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("contentTemplate", "fragments/kontakt");
        return "layout";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(Model model,Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }
        model.addAttribute("contentTemplate", "fragments/access_denied");
        return "layout"; // Ustaw nazwę swojej strony błędu
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String showCreateForm(Model model,Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("contentTemplate", "fragments/createProduct");
        return "layout";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model,Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }

        Iterable<Product> products = productRepository.findAll();
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        model.addAttribute("contentTemplate", "fragments/editProduct");
        return "layout";
    }

    @GetMapping("/product/{id}")
    public String showProductDetails(@PathVariable("id") Long id, Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));

        model.addAttribute("product", product);
        model.addAttribute("contentTemplate", "fragments/productDetails"); // Ustaw nazwę swojego widoku szczegółów produktu
        return "layout";
    }
    @PostMapping("/thanks")
    public String thanks( Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }


        model.addAttribute("contentTemplate", "fragments/thanks"); // Ustaw nazwę swojego widoku szczegółów produktu
        return "layout";
    }
    @GetMapping("/checkout/{id}")
    public String checkout(@PathVariable("id") Long id,Model model,Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            model.addAttribute("isAdmin", isAdmin);
        }
        Iterable<Product> products = productRepository.findAll();
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        model.addAttribute("contentTemplate", "fragments/checkout");
        return "layout";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/products";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public String handleException(Model model,Authentication authentication) {
            if (authentication != null && authentication.isAuthenticated()) {
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                boolean isAdmin = authorities.stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

                model.addAttribute("isAdmin", isAdmin);
            }
            model.addAttribute("contentTemplate", "fragments/general-error");
            return "layout"; // Ustaw nazwę swojej strony błędu ogólnego
        }
    }

}

