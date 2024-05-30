package bot.discord.maho.database.Entity;

import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FunctionChannel")
@EntityListeners(AuditingEntityListener.class)
@Data
public class FunctionChannel {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private Long guildId;
	private String function;
	private Long channelId;

}
