package bot.discord.maho.discord.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import bot.discord.maho.core.Util.Toolbox;
import bot.discord.maho.discord.Command.Command;
import jakarta.annotation.Nonnull;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
@Component
public class DiscordListener extends  ListenerAdapter   {
	private final String packageName = "bot.discord.maho.bookkeeping.discord.Command.Impl.";
	@Autowired
	private ApplicationContext context;
	public void onReady(@Nonnull ReadyEvent event) {
		TextChannel channel = event.getJDA().getTextChannelById(685396765679157258L);
		channel.sendMessage("咕嚕靈波（●′∀‵）ノ♡").queue();
	}
	
	/* 自動把進來的Command首字轉大寫，並且配對Command.Impl套件下的類別
	 *  去呼叫所屬類別的commandAct方法
	 *  */
   public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		String className = Toolbox.capitalizeFirstLetter(event.getName());
		try {
			Command cmd = (Command) context.getBean(Class.forName(packageName+className));
			cmd.commandAct(event);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
