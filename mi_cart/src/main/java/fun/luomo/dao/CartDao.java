package fun.luomo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import fun.luomo.pojo.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface CartDao extends JpaRepository<Cart, String>, JpaSpecificationExecutor<Cart> {

    @Transactional
    @Query(value = "UPDATE cart SET quantity = ?1 ,product_total_price=quantity*product_price WHERE id=?2", nativeQuery = true)
    @Modifying
    void update(int quantity, String id);

    @Transactional
    @Query(value = "UPDATE cart SET product_selected=?1 WHERE id=?2", nativeQuery = true)
    @Modifying
    void selectOne(String select, String id);

    @Transactional
    @Query(value = "UPDATE cart SET product_selected=?1 WHERE id=?2", nativeQuery = true)
    @Modifying
    void unSelectOne(String select, String id);

    List<Cart> findAllByUserId(String id);

    @Query(value = "SELECT count(1) FROM `cart` where user_id= ? AND product_selected='true'", nativeQuery = true)
    int findSelectCount(String id);

    @Query(value = "SELECT count(1) FROM `cart` where user_id= ?", nativeQuery = true)
    int findAllCount(String id);
}
