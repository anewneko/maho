package bot.discord.maho.bookkeeping.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Bot_states", schema = "bookkeeping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class States {
	@Id
	private String key;
	private String value;
	
}
