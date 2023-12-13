package bot.discord.maho.bookkeeping.discord.Command;

import java.util.ArrayList;
import java.util.List;

import org.reflections.Reflections;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandManager {
	private JDA api ;
	private List<SlashCommandData> cmds = new ArrayList<>();
	private String packageName = "bot.discord.maho.bookkeeping.discord.Command.Impl";

	public CommandManager(JDA api) {
		this.api = api ;
	}
	
	
	/*  自動掃描 bot.discord.maho.bookkeeping.discord.Command.Impl 內所有實作Command介面的類別
	 *   並加入cmds內
	 * */
	public void setCommands() {
		Reflections reflections = new Reflections(packageName);
		for (Class<? extends Command> clazz : reflections.getSubTypesOf(Command.class)) 
			try {
				cmds.add(clazz	.getDeclaredConstructor()
										.newInstance()
										.setCommands());
			} catch (Exception e) {
				e.printStackTrace();
			}
        api.updateCommands()
        	.addCommands(cmds)
        	.queue();
	}
}
