package bot.discord.maho.bookkeeping.discord.Command;

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface Command {
	public SlashCommandData setCommands();
	
	public void commandAct();
}
