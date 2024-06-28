package bot.discord.maho.database.Entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import bot.discord.maho.core.Model.DbEntity;
import bot.discord.maho.core.Util.MahoTool;
import bot.discord.maho.security.Model.DiscordUser;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import net.dv8tion.jda.api.entities.User;

@Entity
@Table(name = "Member")
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Member extends DbEntity {
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
	
	public Boolean update(DiscordUser user) {
		var isUpdate = false;
		if (MahoTool.isEmpty(this.avatar)) {
			this.avatar = user.getAvatar();
			isUpdate = true;
		}
		
		if (MahoTool.isEmpty(this.email)) {
			this.email = user.getEmail();
			isUpdate = true;
		}
		
		if (MahoTool.isEmpty(this.username)) {
			this.username = user.getUsername();
			isUpdate = true;
		}
		return isUpdate;
	}
	
	public static Member of(DiscordUser discordUser) {
		var avater = String.format("https://cdn.discordapp.com/avatars/%s/%s.png?size=4096", discordUser.getId(), discordUser.getAvatar());
		return new Member().setAvatar(avater)
		                   .setDiscordId(discordUser.getId())
		                   .setUsername(discordUser.getUsername())
		                   .setEmail(discordUser.getEmail());
	}
	
	public static Member of(User dcUser) {
		return new Member().setDiscordId(dcUser.getIdLong())
						   .setAvatar(dcUser.getAvatarUrl())
						   .setUsername(dcUser.getName());
	}
	
	
	
	


}
