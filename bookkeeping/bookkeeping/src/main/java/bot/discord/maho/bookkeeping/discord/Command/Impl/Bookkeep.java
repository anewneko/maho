package bot.discord.maho.bookkeeping.discord.Command.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bot.discord.maho.bookkeeping.database.entity.Bookkeeping;
import bot.discord.maho.bookkeeping.discord.Command.Command;
import bot.discord.maho.bookkeeping.discord.Dao.BookkeepingRepository;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

@Component
public class Bookkeep implements Command{
	@Autowired
	private BookkeepingRepository bk;
	private final String cmd = this.getClass().getSimpleName().toLowerCase();
	private final String describe = "記帳用";
	
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, describe)
//								 設定使用指令的權限
//								  .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_ROLES))
								  .setGuildOnly(true)
//								  參數的類別 標題 描述 是否必須
								  .addOption(OptionType.STRING, "項目", "The user to ban", true)
								  .addOption(OptionType.INTEGER, "價格", "The user to ban", true);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		String user = event.getUser().getId();
		String item = event.getOption("項目").getAsString();
		Integer price = event.getOption("價格").getAsInt();
		bk.save(new Bookkeeping(user,item,price));
		event.reply("OK").queue();
	}


}
