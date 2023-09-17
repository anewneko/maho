package bot.discord.maho.bookkeeping.database.entity;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

@Entity
@Table(name = "bookkeeping", schema = "bookkeeping")
@Getter
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
	
	
}
