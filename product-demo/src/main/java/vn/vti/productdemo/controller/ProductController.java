package vn.vti.productdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.vti.productdemo.model.Product;
import vn.vti.productdemo.repository.ProductRepository;
import vn.vti.productdemo.service.StorageService;

import java.io.IOException;
import java.util.List;

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

    @PostMapping(value = "/post",consumes = {"multipart/form-data"})
    public String saveProduct(@ModelAttribute("product") Product product, BindingResult result,Model model) throws IOException {

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

}
