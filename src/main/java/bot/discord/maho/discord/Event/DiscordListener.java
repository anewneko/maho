package bot.discord.maho.discord.Event;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import bot.discord.maho.discord.Command.Command;
import jakarta.annotation.Nonnull;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
@Component
public class DiscordListener extends  ListenerAdapter   {
	private boolean isReady = false;
	private Map<String, Command> commands = new HashMap<>();
	
	@Autowired private ApplicationContext app;
	
	public void onReady(@Nonnull ReadyEvent event) {
		initCommands();
		TextChannel channel = event.getJDA().getTextChannelById(685396765679157258L);
		channel.sendMessage("咕嚕靈波（●′∀‵）ノ♡").queue();
	}
	
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		if(!isReady) 
			initCommands();
		commands.get(event.getName()).commandAct(event);
	}
   
	private void initCommands() {
		this.commands = app.getBeansOfType(Command.class);
		isReady = true;
	}
}
