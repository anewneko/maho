package bot.discord.maho.discord.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import bot.discord.maho.discord.Command.Command;
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
	private final static String URL_MSG_TEMPLATE = "[%s][%s] %#s: %s\n url:[%s]\n";
	
	@Override
	public void onReady(@Nonnull ReadyEvent event) {
		initCommands();
		TextChannel channel = event.getJDA().getTextChannelById(685396765679157258L);
		channel.sendMessage("咕嚕靈波（●′∀‵）ノ♡").queue();
	}
	
	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		try {
			if(!isReady) 
				initCommands();
			commands.get(event.getName()).commandAct(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
		var welcome = """
				%s 歡迎來到 プリコネR 戰隊 －【天都紀元】 的 Discord 頻道
				如有意願加入，請輸入 /面試 進行面試。
				""";
		try {
			var guild = event.getGuild();
			guild.getTextChannelById(651478929550606366L)
				 .sendMessage(String.format(welcome, event.getMember().getAsMention()))
				 .queue();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	
	@Override
	  public void onMessageReceived(MessageReceivedEvent event) {
		try {
			var message = event.getMessage();
//			var message = event.getChannel().getIterableHistory().stream().findFirst().orElseGet(null);
			var proxyUrl = message.getAttachments().stream()
								  .map(el -> el.getProxyUrl())
								  .collect(Collectors.toList());
			System.out.printf(message.getAttachments().size() == 0 ? MSG_TEMPLATE : URL_MSG_TEMPLATE,
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
		try {
			
			this.commands = app.getBeansOfType(Command.class)
					.values()
					.stream()
					.collect(Collectors.toMap(e -> e.getCmd(), e -> e));
			isReady = true;
		} catch (Exception e2) {
			e2.printStackTrace();
			isReady = false;
		}
	}
}
