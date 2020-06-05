package fun.luomo.dao;

import fun.luomo.pojo.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface AdDao extends JpaRepository<Ad,String>,JpaSpecificationExecutor<Ad>{
	
}
