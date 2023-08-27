package bot.discord.maho.bookkeeping.discord.Command.Impl;

import bot.discord.maho.bookkeeping.discord.Command.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class Bookkeep implements Command{
	private String cmd;
	private String describe;
	
	
	
	
	public Bookkeep(String describe) {
		this.cmd = this.getClass().getSimpleName().toLowerCase();
		this.describe = describe;
	}


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
	public void commandAct() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
