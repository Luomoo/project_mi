package fun.luomo.dao;


import fun.luomo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Repository
public interface CategoryDao extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {


}
