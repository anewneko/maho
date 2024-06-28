package bot.discord.maho.database.Entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import bot.discord.maho.core.Model.DbEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "ConsumptionItem")
@EntityListeners(AuditingEntityListener.class)
@Getter
public final class ConsumptionItem extends DbEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String  item;
	private Integer  price;
	private String discordId;
	private Long userId;
	private Date createTime = new Date();
	public ConsumptionItem() {}
	public ConsumptionItem setItem(String item) {
		this.item = item;
		return this;
	}
	public ConsumptionItem setPrice(Integer price) {
		this.price = price;
		return this;
	}
	public ConsumptionItem setDiscordId(String discordId) {
		this.discordId = discordId;
		return this;
	}
	public ConsumptionItem setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public ConsumptionItem setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	
}
