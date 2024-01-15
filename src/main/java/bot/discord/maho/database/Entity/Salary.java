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

@Entity
@Table(name = "Salary")
@EntityListeners(AuditingEntityListener.class)
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private Integer price;
	private String discordId;
	private UUID userId;
	private Date givenDate = new Date();
	

	public UUID getId() {
		return id;
	}

	public Salary setId(UUID id) {
		this.id = id;
		return this;
	}

	public Integer getPrice() {
		return price;
	}

	public Salary setPrice(Integer price) {
		this.price = price;
		return this;
	}

	public String getDiscordId() {
		return discordId;
	}

	public Salary setDiscordId(String discordId) {
		this.discordId = discordId;
		return this;
	}

	public UUID getUserId() {
		return userId;
	}

	public Salary setUserId(UUID userId) {
		this.userId = userId;
		return this;
	}

	public Date getGivenDate() {
		return givenDate;
	}

	public Salary setGivenDate(Date givenDate) {
		this.givenDate = givenDate;
		return this;
	}
	
	
	
}
