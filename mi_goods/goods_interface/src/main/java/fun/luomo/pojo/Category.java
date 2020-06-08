package fun.luomo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="category")
public class Category implements Serializable{

	@Id
	private String id;//分类ID

	private String parent_id;//父分类ID
	private String name;//名字
	private String status;//状态
	private Integer sort_order;//排序

	private java.util.Date create_time;//创建时间
	private java.util.Date update_time;//更新时间

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {		
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSort_order() {		
		return sort_order;
	}
	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
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


	
}
