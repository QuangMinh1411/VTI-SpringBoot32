package vn.com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.vti.pagingsorting.model.Product;
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
