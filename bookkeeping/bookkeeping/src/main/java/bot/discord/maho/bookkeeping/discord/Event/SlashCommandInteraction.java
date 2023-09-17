package bot.discord.maho.bookkeeping.discord.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import bot.discord.maho.bookkeeping.discord.Command.Command;
import bot.discord.maho.bookkeeping.discord.util.Toolbox;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
@Component
public class SlashCommandInteraction extends  ListenerAdapter {
	@Autowired
    private ApplicationContext context;
	private final String packageName = "bot.discord.maho.bookkeeping.discord.Command.Impl.";
	
	/* 自動把進來的Command首字轉大寫，並且配對Command.Impl套件下的類別
	 *  去呼叫所屬類別的commandAct方法
	 *  */
	@Override
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
