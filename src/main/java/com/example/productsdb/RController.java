package com.example.productsdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{id}", produces = "application/json")
    public Optional<Product> getProductByID(@PathVariable int id) {
        Optional<Product> product = productService.get(id);

        Link link1 = linkTo(methodOn(RController.class).allProducts()).withRel("allproducts");
        Link link2 = linkTo(methodOn(RController.class).deleteProduct(id)).withRel("delete_by_id");
        Link link3 = linkTo(methodOn(RController.class).addProduct(product.get())).withRel("add_product");

        if(product.isPresent()) {
            product.get().add(link1);
            product.get().add(link2);
            product.get().add(link3);
            return product;
        }
        else
            return null;
    }

    @RequestMapping(value = "/products",produces = "application/json")
    public List<Product> allProducts()
    {
        return productService.listAll();
    }

    @RequestMapping(value = "/delete/{id}", produces = "application/json")
    public Response deleteProduct(@PathVariable int id) {
        Response response=new Response("Product added",false);
        try {
            productService.delete(id);
        }
        catch (Exception e)
        { return response;}
        response.setStatus(true);
        return response;

    }

    @PostMapping("/product/add")
    public Response addProduct(@RequestBody Product product)
    {
        Response response=new Response("Product added",false);
        if((product.getId()!=0) &&(product.getName()!=null) ) {
            productService.save(product);
            response.setStatus(true);
        }
        else
            response.setMessage("failed to add");
        return response;
    }


}
