package com.codegym.product2.controller;

import com.codegym.product2.model.Category;
import com.codegym.product2.model.Product;
import com.codegym.product2.service.CategoryService;
import com.codegym.product2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {

    private String searchString;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/products")
    public ModelAndView showList(@PageableDefault(5) Pageable pageable, @RequestParam("s") Optional<String> s){
        searchString = s.toString().substring(9, s.toString().length()-1);
        Page<Product> products;
        if (!searchString.equals("empt") && s.isPresent() && !searchString.equals("")){
            products = productService.findAllByName(searchString, pageable);
        }else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        modelAndView.addObject("searchString", searchString);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView createProduct(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("product/create");
        if (!bindingResult.hasFieldErrors()){
            productService.save(product);
            modelAndView.addObject("product", product);
            modelAndView.addObject("mess", "Created!");
        }else {
            modelAndView.addObject("product", new Product());
            modelAndView.addAllObjects(bindingResult.getModel());
        }
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit-product/{id}")
    public ModelAndView editProduct(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        if (!bindingResult.hasFieldErrors()){
            productService.save(product);
            modelAndView.addObject("product", product);
            modelAndView.addObject("mess", "Edited!");
        }else {
            modelAndView.addObject("product", new Product());
        }
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("product/delete");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.remove(product.getId());
        return "redirect:products";
    }
}
