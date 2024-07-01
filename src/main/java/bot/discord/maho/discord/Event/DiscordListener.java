package bot.discord.maho.discord.Event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import bot.discord.maho.database.CrudService.Impl.LoginDetailService;
import bot.discord.maho.discord.Command.Command;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
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
//			e.printStackTrace();
		}
	  }
	
	@Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
		try {
			var arr = event.getComponentId().split(":");
			var spKeySvc = getBean(LoginDetailService.class);
			var key = spKeySvc.findById(UUID.fromString(arr[1])).orElseThrow();
			if ( !(key.getIsUsed() && key.getIsVerify()) && key.getIsUsed() || key.getExpireTime().before(new Date())) {
				event.reply(" Speed Key 已失效").queue();
				return;
			}
			switch(arr[0]) {
				case "speed_key_confirm" -> {
					key.setIsVerify(true)
					   .setIsUsed(true);
					event.reply(event.getUser().getName() + " 確認登入").queue();
				}
				case "speed_key_cancel" -> {
					key.setIsUsed(true)
					   .setIsVerify(false);
					event.reply(event.getUser().getName() + " 拒絕登入").queue();
				}
			}
			spKeySvc.save(key);
		}catch(Exception e) {
			e.printStackTrace();
			event.reply("系統錯誤!").queue();
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
	
	private <T> T getBean(Class<T> clazz) {
		return app.getBean(clazz);
	}
}
