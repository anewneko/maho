package bot.discord.maho.discord.Command.Impl;

import org.springframework.stereotype.Component;

import bot.discord.maho.discord.Command.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
@Component
public class Salary implements Command{

	private final String cmd = this.getClass().getSimpleName().toLowerCase();
	private final String describe = "記帳用";
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, describe)
//				 設定使用指令的權限
//				  .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_ROLES))
				  .setGuildOnly(true)
//				  參數的類別 標題 描述 是否必須
				  .addOption(OptionType.INTEGER, "Pay", "The pay", true);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
//		String user = event.getUser().getId();
//		Integer pay = event.getOption("Pay").getAsInt();
//		Date now = new Date();
//		bk.save(new Bookkeeping(user,item,price));
		event.reply("OK").queue();
		
	}

}
