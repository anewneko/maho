package bot.discord.maho.discord.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import bot.discord.maho.discord.Command.Command;
import discord4j.core.object.entity.Role;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
@RequiredArgsConstructor
public class DiscordListener extends  ListenerAdapter   {
	private boolean isReady = false;
	private Map<String, Command> commands = new HashMap<>();
	final private ApplicationContext app;
	
	private final static String MSG_TEMPLATE = "[%s][%s] %#s: %s \n";
	private final static String URL_MSG_TEMPLATE = "[%s][%s] %#s: %s\n url[%s]\n";
	
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
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
		var guild = event.getGuild();
		guild.addRoleToMember(event.getMember(), guild.getRoleById("683988941271334928")).queue();
    }
    
	
	@Override
	  public void onMessageReceived(MessageReceivedEvent event) {
		try {
			var message = event.getChannel().getIterableHistory().stream().findFirst().orElseGet(null);
			var proxyUrl = message.getAttachments().stream()
								  .map(el -> el.getProxyUrl())
								  .collect(Collectors.toList());
			System.out.printf( message.getAttachments().size() == 0 ? MSG_TEMPLATE : URL_MSG_TEMPLATE,
					message.getGuild().getName(),
					message.getChannel().getName(),
					message.getAuthor(),
					message.getContentDisplay(),
					String.join(",", proxyUrl));
		}catch(Exception e) {
			e.printStackTrace();
		}
	  }
   
	private void initCommands() {
		this.commands = app.getBeansOfType(Command.class)
						   .values()
						   .stream()
						   .collect(Collectors.toMap(e -> e.getCmd(), e -> e));
		isReady = true;
	}
}
