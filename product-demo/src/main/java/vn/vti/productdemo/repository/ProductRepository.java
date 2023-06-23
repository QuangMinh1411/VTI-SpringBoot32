package vn.vti.productdemo.repository;

import org.springframework.stereotype.Repository;
import vn.vti.productdemo.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public List<Product> getAll(){
        return this.products;
    }

    public Product create(Product product){
        int id;
        if (products.isEmpty()) {
            id = 1;
        } else {
            Product lastProduct = products.get(products.size() - 1);
            id = lastProduct.getId()+1;
        }
        product.setId(id);
        products.add(product);
        return product;
    }

    public Product edit(Product product){
        var opProduct = get(product.getId());
        if(opProduct.isPresent()){
            var exist = opProduct.get();
            exist.setDetail(product.getDetail());
            exist.setName(product.getName());
            if(!product.getPhoto().getOriginalFilename().isEmpty())
                exist.setPhoto(product.getPhoto());
            return exist;
        }
        return null;
    }

    public Optional<Product> get(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void deleteById(int id) {
        get(id).ifPresent(existed -> products.remove(existed));
    }
}
