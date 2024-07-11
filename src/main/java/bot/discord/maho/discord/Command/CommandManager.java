package bot.discord.maho.discord.Command;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandManager {
	final private JDA bot ;
	final private ApplicationContext app;
	private Set<SlashCommandData> cmds = new HashSet<>();

	public CommandManager(ApplicationContext app,JDA bot) {
		this.app = app;
		this.bot = bot ;
	}
	
	
	public void setCommands() {
		
		var commands = app.getBeansOfType(Command.class);
		commands.forEach((k, v) -> {
			if(!v.getClass().getSimpleName().contains("$"))
				addCommand(v);
		});
		
        bot.updateCommands()
           .addCommands(cmds)
           .queue();
	}
	
	private void addCommand(Command cmd) {
		try {
			cmds.add(cmd.setCommands());
		} catch (Exception e) {e.printStackTrace();}
	}
}
