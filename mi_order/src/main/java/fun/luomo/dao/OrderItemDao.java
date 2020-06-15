package fun.luomo.dao;

import fun.luomo.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface OrderItemDao extends JpaRepository<OrderItem, String>, JpaSpecificationExecutor<OrderItem> {

}
