package bot.discord.maho.discord.Event;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import bot.discord.maho.discord.Command.Command;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
@RequiredArgsConstructor
public class DiscordListener extends  ListenerAdapter   {
	private boolean isReady = false;
	private Map<String, Command> commands = new HashMap<>();
	final private ApplicationContext app;
	
	@Override
	public void onReady(@Nonnull ReadyEvent event) {
		initCommands();
		TextChannel channel = event.getJDA().getTextChannelById(685396765679157258L);
		channel.sendMessage("咕嚕靈波（●′∀‵）ノ♡").queue();
	}
	
	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		if(!isReady) 
			initCommands();
		commands.get(event.getName()).commandAct(event);
	}
	
	@Override
	  public void onMessageReceived(MessageReceivedEvent event) {
		try {
			System.out.printf("[%s] %#s: %s\n",
					event.getChannel().getLatestMessageId(),
					event.getAuthor(),
					event.getChannel().getIterableHistory().stream().findFirst().orElseGet(null));
		}catch(Exception e) {
			e.printStackTrace();
		}
	  }
   
	private void initCommands() {
		this.commands = app.getBeansOfType(Command.class);
		isReady = true;
	}
}
