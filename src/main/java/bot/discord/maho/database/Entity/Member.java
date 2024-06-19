package bot.discord.maho.database.Entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import bot.discord.maho.web.Page.Model.POJO.DiscordUser;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "Member")
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String username;
	private String avatar;
	private String speedkey;
	private Long discordId;
	private String email;
	private Boolean isManager = false;
	private Date createTime;
	
	public Member() {
		this.createTime = new Date();
	}

	public Member setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public Member setIsManager(Boolean isManager) {
		this.isManager = isManager;
		return this;
	}

	public Member setUsername(String username) {
		this.username = username;
		return this;
	}

	public Member setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public Member setSpeedkey(String speedkey) {
		this.speedkey = speedkey;
		return this;
	}

	public Member setDiscordId(Long discordId) {
		this.discordId = discordId;
		return this;
	}

	public Member setEmail(String email) {
		this.email = email;
		return this;
	}

	public Member setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	public static Member of(DiscordUser discordUser) {
		var avater = String.format("https://cdn.discordapp.com/avatars/%s/%s.png?size=4096", discordUser.getId(), discordUser.getAvatar());
		return new Member().setAvatar(avater)
		                   .setDiscordId(discordUser.getId())
		                   .setUsername(discordUser.getUsername())
		                   .setEmail(discordUser.getEmail());
	}	
	
	


}
