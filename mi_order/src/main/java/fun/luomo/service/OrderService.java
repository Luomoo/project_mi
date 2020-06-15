package fun.luomo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import fun.luomo.dao.OrderDao;
import fun.luomo.pojo.Order;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Order> findSearch(Map whereMap, int page, int size) {
		Specification<Order> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return orderDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Order> findSearch(Map whereMap) {
		Specification<Order> specification = createSpecification(whereMap);
		return orderDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Order findById(String id) {
		return orderDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param order
	 */
	public void add(Order order) {
		order.setId( idWorker.nextId()+"" );
		orderDao.save(order);
	}

	/**
	 * 修改
	 * @param order
	 */
	public void update(Order order) {
		orderDao.save(order);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		orderDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Order> createSpecification(Map searchMap) {

		return new Specification<Order>() {

			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 订单id
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 物流名称
                if (searchMap.get("shipping_name")!=null && !"".equals(searchMap.get("shipping_name"))) {
                	predicateList.add(cb.like(root.get("shipping_name").as(String.class), "%"+(String)searchMap.get("shipping_name")+"%"));
                }
                // 物流单号
                if (searchMap.get("shipping_code")!=null && !"".equals(searchMap.get("shipping_code"))) {
                	predicateList.add(cb.like(root.get("shipping_code").as(String.class), "%"+(String)searchMap.get("shipping_code")+"%"));
                }
                // 用户名称
                if (searchMap.get("username")!=null && !"".equals(searchMap.get("username"))) {
                	predicateList.add(cb.like(root.get("username").as(String.class), "%"+(String)searchMap.get("username")+"%"));
                }
                // 买家留言
                if (searchMap.get("buyer_message")!=null && !"".equals(searchMap.get("buyer_message"))) {
                	predicateList.add(cb.like(root.get("buyer_message").as(String.class), "%"+(String)searchMap.get("buyer_message")+"%"));
                }
                // 是否评价
                if (searchMap.get("buyer_rate")!=null && !"".equals(searchMap.get("buyer_rate"))) {
                	predicateList.add(cb.like(root.get("buyer_rate").as(String.class), "%"+(String)searchMap.get("buyer_rate")+"%"));
                }
                // 收货人
                if (searchMap.get("receiver_contact")!=null && !"".equals(searchMap.get("receiver_contact"))) {
                	predicateList.add(cb.like(root.get("receiver_contact").as(String.class), "%"+(String)searchMap.get("receiver_contact")+"%"));
                }
                // 收货人手机
                if (searchMap.get("receiver_mobile")!=null && !"".equals(searchMap.get("receiver_mobile"))) {
                	predicateList.add(cb.like(root.get("receiver_mobile").as(String.class), "%"+(String)searchMap.get("receiver_mobile")+"%"));
                }
                // 收货人地址
                if (searchMap.get("receiver_address")!=null && !"".equals(searchMap.get("receiver_address"))) {
                	predicateList.add(cb.like(root.get("receiver_address").as(String.class), "%"+(String)searchMap.get("receiver_address")+"%"));
                }
                // 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
                if (searchMap.get("source_type")!=null && !"".equals(searchMap.get("source_type"))) {
                	predicateList.add(cb.like(root.get("source_type").as(String.class), "%"+(String)searchMap.get("source_type")+"%"));
                }
                // 交易流水号
                if (searchMap.get("transaction_id")!=null && !"".equals(searchMap.get("transaction_id"))) {
                	predicateList.add(cb.like(root.get("transaction_id").as(String.class), "%"+(String)searchMap.get("transaction_id")+"%"));
                }
                // 订单状态,0:未完成,1:已完成，2：已退货
                if (searchMap.get("order_status")!=null && !"".equals(searchMap.get("order_status"))) {
                	predicateList.add(cb.like(root.get("order_status").as(String.class), "%"+(String)searchMap.get("order_status")+"%"));
                }
                // 支付状态,0:未支付，1：已支付，2：支付失败
                if (searchMap.get("pay_status")!=null && !"".equals(searchMap.get("pay_status"))) {
                	predicateList.add(cb.like(root.get("pay_status").as(String.class), "%"+(String)searchMap.get("pay_status")+"%"));
                }
                // 发货状态,0:未发货，1：已发货，2：已收货
                if (searchMap.get("consign_status")!=null && !"".equals(searchMap.get("consign_status"))) {
                	predicateList.add(cb.like(root.get("consign_status").as(String.class), "%"+(String)searchMap.get("consign_status")+"%"));
                }
                // 是否删除
                if (searchMap.get("is_delete")!=null && !"".equals(searchMap.get("is_delete"))) {
                	predicateList.add(cb.like(root.get("is_delete").as(String.class), "%"+(String)searchMap.get("is_delete")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
