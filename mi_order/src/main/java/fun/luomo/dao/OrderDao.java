package fun.luomo.dao;

import fun.luomo.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface OrderDao extends JpaRepository<Order,String>,JpaSpecificationExecutor<Order>{
	
}
