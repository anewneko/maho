package bot.discord.maho.discord.Command;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandManager {
	final private JDA api ;
	final private ApplicationContext app;
	private Set<SlashCommandData> cmds = new HashSet<>();

	public CommandManager(ApplicationContext app,JDA api) {
		this.app = app;
		this.api = api ;
	}
	
	
	public void setCommands() {
		
		var commands = app.getBeansOfType(Command.class);
		commands.forEach((k, v) -> addCommand(v));
		
        api.updateCommands()
        	.addCommands(cmds)
        	.queue();
	}
	
	private void addCommand(Command cmd) {
		try {
			cmds.add(cmd.setCommands());
		} catch (Exception e) {/*e.printStackTrace();*/}
	}
}
