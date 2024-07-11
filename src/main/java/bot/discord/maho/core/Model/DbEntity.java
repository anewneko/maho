package bot.discord.maho.core.Model;

import java.util.UUID;
public class DbEntity {
	protected UUID id;
	
	public UUID getId() {
		return id;
	}
	
	public DbEntity setId(UUID id) {
		this.id = id;
		return this;
	}
	

}
