package bot.discord.maho.bookkeeping.discord.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandManager {
	private JDA api ;
	private List<SlashCommandData> cmds;
	private String packageName = "bot.discord.maho.bookkeeping.discord.Command.Impl";

	public CommandManager(JDA api) {
		this.api = api ;
		this.cmds = new ArrayList<>();
	}
	
	
	/*  自動掃描 bot.discord.maho.bookkeeping.discord.Command.Impl 內所有實作Command介面的類別
	 *   並加入cmds內
	 * */
	public void setCommands() {
		Reflections reflections = new Reflections(packageName);
		Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);
		for (Class<? extends Command> clazz : classes) {
			try {
				Command cmd = clazz.getDeclaredConstructor().newInstance();
				cmds.add(cmd.setCommands());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        	api.updateCommands().addCommands(cmds).queue();
		}
		
	
	
	
	
}
