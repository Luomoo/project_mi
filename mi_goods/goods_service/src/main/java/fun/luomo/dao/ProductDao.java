package fun.luomo.dao;

import fun.luomo.pojo.Product;
import fun.luomo.pojo.ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Repository
public interface ProductDao extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByCategoryId(int id);

}
