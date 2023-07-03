package vn.com.vti.pagingsorting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.vti.pagingsorting.model.Product;
import vn.com.vti.pagingsorting.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(service.saveProduct(product),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> allProducts = service.findAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{field}")
    public ResponseEntity<List<Product>> getProductsWithSort(@PathVariable String field) {
        List<Product> allProducts = service.findProductsWithSorting(field);
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<List<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        List<Product> productsWithPagination = service.findProductsWithPagination(offset, pageSize);

        return new ResponseEntity<>(productsWithPagination,HttpStatus.OK);
    }
    @GetMapping("/pagingSql")
    public ResponseEntity<?> getPagination(@RequestParam("offset")int offset,@RequestParam("pageSize")int pageSize){
        return new ResponseEntity<>(service.getPagingProduct(offset,pageSize),HttpStatus.OK);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Product> productsWithPagination = service.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new ResponseEntity<>(productsWithPagination,HttpStatus.OK);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<?> getInfo(@PathVariable String name){
        return new ResponseEntity<>(service.getInfo(name),HttpStatus.OK);
    }
}
