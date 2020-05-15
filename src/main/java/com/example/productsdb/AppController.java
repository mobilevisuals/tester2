package com.example.productsdb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    private ProductService service;

    @Autowired
    public AppController(ProductService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("theproduct", product);
        return "new_product";
    }

    @PostMapping("/save")
    public String saveProduct(Product product, BindingResult result) {
        if (result.hasErrors())
            return "error";
        service.save(product);
        return "redirect:/";
    }

   /* @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product", product);

        return mav;
    }*/

    @RequestMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable(name = "id") int id,Model model) {
        Product product = service.get(id);
        model.addAttribute("product_to_edit", product);
        return "edit_product";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}
