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
@Table(name="ad")
public class Ad implements Serializable{

	@Id
	private String id;//ID

	private String name;//广告名称
	private String position;//广告位置
	private java.util.Date start_time;//开始时间
	private java.util.Date end_time;//到期时间
	private String status;//状态
	private String image;//图片地址
	private String url;//URL
	private String remarks;//备注

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {		
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	public java.util.Date getStart_time() {		
		return start_time;
	}
	public void setStart_time(java.util.Date start_time) {
		this.start_time = start_time;
	}

	public java.util.Date getEnd_time() {		
		return end_time;
	}
	public void setEnd_time(java.util.Date end_time) {
		this.end_time = end_time;
	}

	public String getStatus() {		
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {		
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {		
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemarks() {		
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	
}
