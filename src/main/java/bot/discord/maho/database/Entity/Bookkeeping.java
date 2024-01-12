package bot.discord.maho.database.Entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookkeeping", schema = "bookkeeping")
public class Bookkeeping{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookkeepingId;
	@Column(insertable = false)
	private Timestamp createTime;
	private String  item;
	private Integer  price;
	private String discordId;
	
	public Bookkeeping() {}
	
	
	public Bookkeeping(String userId , String item , Integer price) {
		this.discordId = userId;
		this.item = item;
		this.price = price;
	}
	
	public Bookkeeping setBookkeepingId(Integer bookkeepingId) {
		this.bookkeepingId = bookkeepingId;
		return this;
	}
	
	
	public Bookkeeping setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Bookkeeping setItem(String item) {
		this.item = item;
		return this;
	}
	
	
	public Bookkeeping setPrice(Integer price) {
		this.price = price;
		return this;
	}
	
	
	public Bookkeeping setDiscordId(String discordId) {
		this.discordId = discordId;
		return this;
	}


	public Integer getBookkeepingId() {
		return bookkeepingId;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public String getItem() {
		return item;
	}


	public Integer getPrice() {
		return price;
	}


	public String getDiscordId() {
		return discordId;
	}
	
	
	
	
}
