package bot.discord.maho.bookkeeping.database.entity;

import java.sql.Timestamp;

import bot.discord.maho.bookkeeping.core.pojo.CorePojo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookkeeping", schema = "bookkeeping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookkeeping extends CorePojo{
	
	
	private static final long serialVersionUID = -9077990332142388724L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookkeepingId;
	@Column(insertable = false)
	private Timestamp createTime;
	private String  item;
	private Integer  price;
	private String discordId;
	
	
	public Bookkeeping(String userId , String item , Integer price) {
		this.discordId = userId;
		this.item = item;
		this.price = price;
	}
	
	public Integer getBookkeepingId() {
		return bookkeepingId;
	}
	public void setBookkeepingId(Integer bookkeepingId) {
		this.bookkeepingId = bookkeepingId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDicordId() {
		return discordId;
	}
	public void setDicordId(String dicordId) {
		this.discordId = dicordId;
	}
	
	
}
