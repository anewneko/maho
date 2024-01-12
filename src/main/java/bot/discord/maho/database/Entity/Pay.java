package bot.discord.maho.database.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	private Integer price;
	private String discordId;
	
	public Pay() {
	}
	
	public Pay(Integer id, Date date, Integer price, String discordId) {
		this.id = id;
		this.date = date;
		this.price = price;
		this.discordId = discordId;
	}
	
	public Pay setId(Integer id) {
		this.id = id;
		return this;
	}
	public Pay setDate(Date date) {
		this.date = date;
		return this;
	}
	public Pay setPrice(Integer price) {
		this.price = price;
		return this;
	}
	public Pay setDiscordId(String discordId) {
		this.discordId = discordId;
		return this;
	}
	public Integer getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public Integer getPrice() {
		return price;
	}
	public String getDiscordId() {
		return discordId;
	}
	
	
}
