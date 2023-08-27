package bot.discord.maho.bookkeeping.discord.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import bot.discord.maho.bookkeeping.discord.Command.Command;
import bot.discord.maho.bookkeeping.discord.util.Toolbox;
import jakarta.annotation.Nonnull;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class DiscordListener extends  ListenerAdapter  {
	@Autowired
    private ApplicationContext context;
	
	

	@Override
	public void onReady(@Nonnull ReadyEvent event) {
		TextChannel channel = event.getJDA().getTextChannelById(685396765679157258L);
		channel.sendMessage("咕嚕靈波（●′∀‵）ノ♡").queue();
	}
	
	@Override
	public void onGuildJoin(GuildJoinEvent event)
    {
//        JDA api = event.getJDA();
//        User user = api.getSelfUser() ; 
    }
	
	
	/* 自動把進來的Command首字轉大寫，並且配對Command.Impl套件下的類別
	 *  去呼叫所屬類別的commandAct方法
	 *  */
	@Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		String packageName = "bot.discord.maho.bookkeeping.discord.Command.Impl.";
		String className = Toolbox.capitalizeFirstLetter(event.getName());
		try {
			Class<?> clazz = Class.forName(packageName+className);
			Command cmd = (Command) context.getBean(clazz);
			cmd.commandAct(event);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
