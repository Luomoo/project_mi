package fun.luomo.dao;

import fun.luomo.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductDao extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByCategoryId(int id);
}
