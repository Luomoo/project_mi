package fun.luomo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="order_item")
public class OrderItem implements Serializable{

	@Id
	private String id;//ID

	private Integer category_id;//分类
	private Long product_id;//商品ID
	private String order_id;//订单ID
	private String name;//商品名称
	private Integer price;//单价
	private Integer num;//数量
	private Integer money;//总金额
	private Integer pay_money;//实付金额
	private String image;//图片地址
	private Integer weight;//重量
	private Integer post_fee;//运费
	private String is_return;//是否退货,0:未退货，1：已退货

	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getCategory_id() {		
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Long getProduct_id() {		
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getOrder_id() {		
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {		
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNum() {		
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getMoney() {		
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getPay_money() {		
		return pay_money;
	}
	public void setPay_money(Integer pay_money) {
		this.pay_money = pay_money;
	}

	public String getImage() {		
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public Integer getWeight() {		
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getPost_fee() {		
		return post_fee;
	}
	public void setPost_fee(Integer post_fee) {
		this.post_fee = post_fee;
	}

	public String getIs_return() {		
		return is_return;
	}
	public void setIs_return(String is_return) {
		this.is_return = is_return;
	}


	
}
