package com.example.productsdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    public Product getProductByID(@PathVariable int id) {
        Product product = productService.get(id);

        Link link1 = linkTo(methodOn(RController.class).allProducts()).withRel("allproducts");
      //  Link link2 = linkTo(methodOn(RController.class).deleteProduct(id)).withRel("delete_by_id");
        /*Book res=null;
        Link link1=linkTo(methodOn(BookController.class).deleteByID(id)).withRel("delete_by_id");
        for(Book book:bookList)
        {
            if(book.getId()==id)
                res=book;
        }
        res.add(link1);
        Link link2=linkTo(methodOn(BookController.class).allBook()).withRel("all_books");
        res.add(link2);
        Link link3=linkTo(methodOn(BookController.class).addBook(res)).withRel("add_book");
        res.add(link3);*/
product.add(link1);
        return product;
    }

    @RequestMapping(value = "/products",produces = "application/json")
    public List<Product> allProducts()
    {
        return productService.listAll();
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public int deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return id;
    }


}
