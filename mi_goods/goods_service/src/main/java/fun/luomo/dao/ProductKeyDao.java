package fun.luomo.dao;

import fun.luomo.pojo.Product;
import fun.luomo.pojo.ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Repository
public interface ProductKeyDao extends JpaRepository<ProductKey, String>, JpaSpecificationExecutor<Product> {

}
