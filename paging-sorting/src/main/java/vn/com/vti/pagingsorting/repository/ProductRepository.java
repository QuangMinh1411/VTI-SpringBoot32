package vn.com.vti.pagingsorting.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.com.vti.pagingsorting.dto.ProductInfo;
import vn.com.vti.pagingsorting.model.Product;

import java.util.List;

@Component
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT new vn.com.vti.pagingsorting.dto.ProductInfo(p.name,p.price) FROM Product p WHERE p.name LIKE %?1%")
    List<ProductInfo> getProductInfo(String name);

    @Query(value = "SELECT * FROM Product p LIMIT ?2 OFFSET ?1",nativeQuery = true)
    List<Product> pagingWithQuery(int offset,int pageSize);
}
