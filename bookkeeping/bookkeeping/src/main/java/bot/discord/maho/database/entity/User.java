package bot.discord.maho.database.entity;

import bot.discord.maho.core.pojo.CorePojo;
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
@Table(name = "User", schema = "bookkeeping")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends CorePojo {
	private static final long serialVersionUID = -8565828708187381759L;

	@Id
	private String discordId;
	private String firstKey;
	private String secondKey;
	private String Name;
	
	
	public User setDiscordId(String discordId) {
		this.discordId = discordId;
		return this;
	}
	public User setFirstKey(String firstKey) {
		this.firstKey = firstKey;
		return this;
		
	}
	public User setSecondKey(String secondKey) {
		this.secondKey = secondKey;
		return this;
	}
	public User setName(String name) {
		Name = name;
		return this;
	}
	
	
	}
