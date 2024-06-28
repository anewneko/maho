package bot.discord.maho.database.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import bot.discord.maho.core.Model.BatchQuery;
import bot.discord.maho.database.Entity.ConsumptionItem;

public interface ConsumptionItemRepository extends JpaRepository<ConsumptionItem, UUID> , BatchQuery<ConsumptionItem, UUID> {
	

}
