package com.training.shop.controllers;

import com.training.shop.entity.Product;
import com.training.shop.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> catalogMain(Model model) {
        System.out.println(productService.findAll());
        return  productService.findAll();
//        model.addAttribute("products", products);
//        return "catalog";
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void catalogPostDelete(@PathVariable(value = "id") Long id, Model model) {
        productService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/edit")
    public String catalogGetEdit(@PathVariable(value = "id") Long id, Model model) {
        if (!productService.existsById(id)) {
            return "redirect:/catalog";
        }

        Product product = productService.findById(id);
        model.addAttribute("post", product);
        return "catalog-edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/edit")
    public String catalogPostEdit(@PathVariable(value = "id") Long id,
                                  @RequestParam(value = "title") String title,
                                  @RequestParam(value = "price") BigDecimal price,
                                  @RequestParam(value = "description") String description,
                                  Model model) {
        Product product = productService.findById(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        productService.update(product);
        return "redirect:/catalog";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String catalogAdd(Model model) {
        return "catalog-add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String catalogPostAdd(@RequestParam(value = "title") String title,
                                 @RequestParam(value = "price") BigDecimal price,
                                 @RequestParam(value = "description") String description,
                                 Model model) {
        Product product = new Product(title, description, price);
        productService.create(product);
        return "redirect:/catalog";
    }
}
