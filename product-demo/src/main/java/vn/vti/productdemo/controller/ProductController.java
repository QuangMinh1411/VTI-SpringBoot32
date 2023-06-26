package vn.vti.productdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vn.vti.productdemo.exception.StorageException;
import vn.vti.productdemo.model.Product;
import vn.vti.productdemo.repository.ProductRepository;
import vn.vti.productdemo.service.StorageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepo;

    private final StorageService storageService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("product",new Product());
        return "home";
    }

    @GetMapping("/listAll")
    public String listAll(Model model) {
        List<Product> products = productRepo.getAll();

        model.addAttribute("products", products);

        return "listAll";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable("id")int id, Model model){
        Optional<Product> product = productRepo.get(id);
        if(product.isPresent()){
            model.addAttribute("product",product.get());
            return "productInfo";
        }
        return "home";
    }

    @GetMapping("/product/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Optional<Product> product = productRepo.get(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        return "home";
    }

    @PostMapping(value = "/post",consumes = {"multipart/form-data"})
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) throws Exception {
        if (product.getPhoto().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("product", "photo","Photo is required"));
        }
        if (result.hasErrors()) {
            return "home";
        }


        if (product.getId() > 0) {
            productRepo.edit(product);
        } else {
            productRepo.create(product);
        }
        storageService.uploadFile(product.getPhoto(), product.getId());
        model.addAttribute("products", productRepo.getAll());
        return "redirect:/listAll";

    }

    @GetMapping("/product/delete/{id}")
    public String deletePerson(@PathVariable("id") int id, Model model) {
        storageService.deleteFile(id);
        productRepo.deleteById(id);
        model.addAttribute("product", productRepo.getAll());
        return "redirect:/listAll";
    }

    @ExceptionHandler(StorageException.class)
    public String handleStorageFileNotFound(StorageException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "failure";
    }

}
