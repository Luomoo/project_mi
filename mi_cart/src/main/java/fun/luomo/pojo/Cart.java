package fun.luomo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="cart")
public class Cart implements Serializable{

	@Id
	private String id;

	private String userId;
	private String productId;
	private Integer quantity;
	private String product_name;
	private String product_subtitle;
	private String product_main_image;
	private Integer product_price;
	private String product_status;
	private Integer product_total_price;
	private String product_stock;
	private String product_selected;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {		
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {		
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {		
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getProduct_name() {		
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_subtitle() {		
		return product_subtitle;
	}
	public void setProduct_subtitle(String product_subtitle) {
		this.product_subtitle = product_subtitle;
	}

	public String getProduct_main_image() {		
		return product_main_image;
	}
	public void setProduct_main_image(String product_main_image) {
		this.product_main_image = product_main_image;
	}

	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}

	public String getProduct_status() {		
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}

	public Integer getProduct_total_price() {
		return product_total_price;
	}
	public void setProduct_total_price(Integer product_total_price) {
		this.product_total_price = product_total_price;
	}

	public String getProduct_stock() {		
		return product_stock;
	}
	public void setProduct_stock(String product_stock) {
		this.product_stock = product_stock;
	}

	public String getProduct_selected() {		
		return product_selected;
	}
	public void setProduct_selected(String product_selected) {
		this.product_selected = product_selected;
	}


	
}
