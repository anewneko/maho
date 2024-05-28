package bot.discord.maho.database.Entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ConsumptionItem")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ConsumptionItem{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String  item;
	private Integer  price;
	private String discordId;
	private Long userId;
	private Date createTime = new Date();
	public ConsumptionItem() {}
	public UUID getId() {
		return id;
	}
	public ConsumptionItem setId(UUID id) {
		this.id = id;
		return this;
	}
	public String getItem() {
		return item;
	}
	public ConsumptionItem setItem(String item) {
		this.item = item;
		return this;
	}
	public Integer getPrice() {
		return price;
	}
	public ConsumptionItem setPrice(Integer price) {
		this.price = price;
		return this;
	}
	public String getDiscordId() {
		return discordId;
	}
	public ConsumptionItem setDiscordId(String discordId) {
		this.discordId = discordId;
		return this;
	}
	public ConsumptionItem setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public ConsumptionItem setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	
}
