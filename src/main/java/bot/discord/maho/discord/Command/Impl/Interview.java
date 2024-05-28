package bot.discord.maho.discord.Command.Impl;

import org.springframework.stereotype.Component;

import bot.discord.maho.discord.Command.Command;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

@Component
@RequiredArgsConstructor
public class Interview implements Command {
	
	private final static String cmd = "面試";
	private final static String description = "使用此指令後，開始面試。";
	
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, description);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		System.out.println("面試");
		event.reply("OK").queue();
	}
	
	@Override
	public String getCmd() {
		return cmd;
	}

}
