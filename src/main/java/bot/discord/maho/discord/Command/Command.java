package bot.discord.maho.discord.Command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface Command {
	public SlashCommandData setCommands();
	
	public void commandAct(SlashCommandInteractionEvent event);
	
	default public String getCmd() {
		return this.getClass().getSimpleName().toLowerCase();
	}
	
}
