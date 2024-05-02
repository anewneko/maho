package bot.discord.maho.discord.Command.Impl;

import org.springframework.stereotype.Component;

import bot.discord.maho.discord.Command.Command;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

@Component
@RequiredArgsConstructor
public class Register implements Command {
	private final String cmd = this.getClass().getSimpleName().toLowerCase();
	private final String describe = "註冊用";

	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, describe)
					   .setGuildOnly(true)
					   .addOption(OptionType.STRING, "firstkey", "The first key for login maho robot's web.", true)
					   .addOption(OptionType.STRING, "secondkey", "Set second key for web.", true);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
//		var user = new User();
		event.getOption("firstkey").getAsString();
		event.getOption("secondkey").getAsString();
//		var member = event.getMember();
		event.reply("OK").queue();

	}

}
