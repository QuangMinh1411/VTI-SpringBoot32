package vn.com.vti.pagingsorting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.com.vti.pagingsorting.dto.ProductInfo;
import vn.com.vti.pagingsorting.model.Product;
import vn.com.vti.pagingsorting.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }


    public List<Product> findProductsWithSorting(String field){
        return  repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public List<Product> findProductsWithPagination(int offset, int pageSize){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize));
        return products.getContent().stream().map(product -> product).collect(Collectors.toList());
    }

    public Page<Product> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }

    public List<Product> getPagingProduct(int offset,int pageSize){
        return repository.pagingWithQuery(offset,pageSize);
    }


    public List<ProductInfo> getInfo(String name){
        return repository.getProductInfo(name);
    }
}
