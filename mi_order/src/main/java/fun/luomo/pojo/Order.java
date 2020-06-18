package fun.luomo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="order")
public class Order implements Serializable{

	@Id
	private String id;//订单id

	private Integer total_num;//数量合计
	private Integer total_money;//金额合计
	private Integer pre_money;//优惠金额
	private Integer post_fee;//邮费
	private Integer pay_money;//实付金额
	private Date create_time;//订单创建时间
	private Date update_time;//订单更新时间
	private Date pay_time;//付款时间
	private Date consign_time;//发货时间
	private Date end_time;//交易完成时间
	private Date close_time;//交易关闭时间
	private String shipping_name;//物流名称
	private String shipping_code;//物流单号
	private String username;//用户名称
	private String buyer_message;//买家留言
	private String buyer_rate;//是否评价
	private String receiver_contact;//收货人
	private String receiver_mobile;//收货人手机
	private String receiver_address;//收货人地址
	private String source_type;//订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
	private String transaction_id;//交易流水号
	private String order_status;//订单状态,0:未完成,1:已完成，2：已退货
	private String pay_status;//支付状态,0:未支付，1：已支付，2：支付失败
	private String consign_status;//发货状态,0:未发货，1：已发货，2：已收货
	private String is_delete;//是否删除

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getTotal_num() {		
		return total_num;
	}
	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}

	public Integer getTotal_money() {		
		return total_money;
	}
	public void setTotal_money(Integer total_money) {
		this.total_money = total_money;
	}

	public Integer getPre_money() {		
		return pre_money;
	}
	public void setPre_money(Integer pre_money) {
		this.pre_money = pre_money;
	}

	public Integer getPost_fee() {		
		return post_fee;
	}
	public void setPost_fee(Integer post_fee) {
		this.post_fee = post_fee;
	}

	public Integer getPay_money() {		
		return pay_money;
	}
	public void setPay_money(Integer pay_money) {
		this.pay_money = pay_money;
	}

	public java.util.Date getCreate_time() {		
		return create_time;
	}
	public void setCreate_time(java.util.Date create_time) {
		this.create_time = create_time;
	}

	public java.util.Date getUpdate_time() {		
		return update_time;
	}
	public void setUpdate_time(java.util.Date update_time) {
		this.update_time = update_time;
	}

	public java.util.Date getPay_time() {		
		return pay_time;
	}
	public void setPay_time(java.util.Date pay_time) {
		this.pay_time = pay_time;
	}

	public java.util.Date getConsign_time() {		
		return consign_time;
	}
	public void setConsign_time(java.util.Date consign_time) {
		this.consign_time = consign_time;
	}

	public java.util.Date getEnd_time() {		
		return end_time;
	}
	public void setEnd_time(java.util.Date end_time) {
		this.end_time = end_time;
	}

	public java.util.Date getClose_time() {		
		return close_time;
	}
	public void setClose_time(java.util.Date close_time) {
		this.close_time = close_time;
	}

	public String getShipping_name() {		
		return shipping_name;
	}
	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}

	public String getShipping_code() {		
		return shipping_code;
	}
	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}

	public String getUsername() {		
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getBuyer_message() {		
		return buyer_message;
	}
	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}

	public String getBuyer_rate() {		
		return buyer_rate;
	}
	public void setBuyer_rate(String buyer_rate) {
		this.buyer_rate = buyer_rate;
	}

	public String getReceiver_contact() {		
		return receiver_contact;
	}
	public void setReceiver_contact(String receiver_contact) {
		this.receiver_contact = receiver_contact;
	}

	public String getReceiver_mobile() {		
		return receiver_mobile;
	}
	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}

	public String getReceiver_address() {		
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}

	public String getSource_type() {		
		return source_type;
	}
	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}

	public String getTransaction_id() {		
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOrder_status() {		
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getPay_status() {		
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getConsign_status() {		
		return consign_status;
	}
	public void setConsign_status(String consign_status) {
		this.consign_status = consign_status;
	}

	public String getIs_delete() {		
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}


	
}
