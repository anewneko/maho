package bot.discord.maho.bookkeeping.database.entity;

import bot.discord.maho.bookkeeping.core.pojo.CorePojo;
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
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends CorePojo {
	private static final long serialVersionUID = -8565828708187381759L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String account;
	private String password;
	private String discordId;
	}
