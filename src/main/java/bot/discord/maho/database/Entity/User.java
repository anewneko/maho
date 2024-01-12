package bot.discord.maho.database.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "User", schema = "bookkeeping")
public class User {

	@Id
	private String discordId;
	private String firstKey;
	private String secondKey;
	private String name;
	
	public User() {
	}
	
	public User(String discordId, String firstKey, String secondKey, String name) {
		this.discordId = discordId;
		this.firstKey = firstKey;
		this.secondKey = secondKey;
		this.name = name;
	}
	
	
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
		this.name = name;
		return this;
	}
	public String getDiscordId() {
		return discordId;
	}
	public String getFirstKey() {
		return firstKey;
	}
	public String getSecondKey() {
		return secondKey;
	}
	public String getName() {
		return name;
	}
	
	
	
	
	}
