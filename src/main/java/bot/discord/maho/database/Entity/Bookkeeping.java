package bot.discord.maho.database.Entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bookkeeping")
@EntityListeners(AuditingEntityListener.class)
public class Bookkeeping{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(insertable = false)
	private String  project;
	private Integer  price;
	private String discordId;
	private UUID userId;
	private Date createTime = new Date();
	public Bookkeeping() {}
	public UUID getId() {
		return id;
	}
	public Bookkeeping setId(UUID id) {
		this.id = id;
		return this;
	}
	public String getProject() {
		return project;
	}
	public Bookkeeping setProject(String project) {
		this.project = project;
		return this;
	}
	public Integer getPrice() {
		return price;
	}
	public Bookkeeping setPrice(Integer price) {
		this.price = price;
		return this;
	}
	public String getDiscordId() {
		return discordId;
	}
	public Bookkeeping setDiscordId(String discordId) {
		this.discordId = discordId;
		return this;
	}
	public UUID getUserId() {
		return userId;
	}
	public Bookkeeping setUserId(UUID userId) {
		this.userId = userId;
		return this;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Bookkeeping setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	
}
