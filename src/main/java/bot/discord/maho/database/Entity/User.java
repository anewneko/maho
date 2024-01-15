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
@Table(name = "User")
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String nick;
	private String account;
	private String password;
	private String firstKey;
	private String secondKey;
	private String discordId;
	private String email;
	private String status;
	private Date createTime = new Date();

	public UUID getId() {
		return id;
	}

	public User setId(UUID id) {
		this.id = id;
		return this;
	}

	public String getNick() {
		return nick;
	}

	public User setNick(String nick) {
		this.nick = nick;
		return this;
	}

	public String getAccount() {
		return account;
	}

	public User setAccount(String account) {
		this.account = account;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getFirstKey() {
		return firstKey;
	}

	public User setFirstKey(String firstKey) {
		this.firstKey = firstKey;
		return this;
	}

	public String getSecondKey() {
		return secondKey;
	}

	public User setSecondKey(String secondKey) {
		this.secondKey = secondKey;
		return this;
	}

	public String getDiscordId() {
		return discordId;
	}

	public User setDiscordId(String discordId) {
		this.discordId = discordId;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public User setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public User setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

}
