package bot.discord.maho.bookkeeping.discord.Command;

import java.util.ArrayList;
import java.util.List;

import bot.discord.maho.bookkeeping.discord.Command.Impl.Bookkeep;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CommandManager {
	private JDA api ;
	private List<SlashCommandData> cmds;

	public CommandManager(JDA api) {
		this.api = api ;
		this.cmds = new ArrayList<>();
	}
	
	public void setCommands() {
		cmds.add(new Bookkeep("記帳用").setCommands());
		api.updateCommands().addCommands(cmds.get(0)).queue();
		
	}
}
