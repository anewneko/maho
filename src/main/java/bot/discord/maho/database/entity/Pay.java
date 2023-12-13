package bot.discord.maho.database.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Pay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	private Integer price;
	private String discordId;
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
	
	
}
