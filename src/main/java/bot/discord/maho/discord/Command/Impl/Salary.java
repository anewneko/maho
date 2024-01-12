package bot.discord.maho.discord.Command.Impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bot.discord.maho.database.Entity.Pay;
import bot.discord.maho.database.Repository.PayRepository;
import bot.discord.maho.discord.Command.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
@Component
public class Salary implements Command{

	private final String cmd = this.getClass().getSimpleName().toLowerCase();
	private final String describe = "記帳用";
	@Autowired
	private PayRepository repo;
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, describe)
					   .setGuildOnly(true)
					   .addOption(OptionType.INTEGER, "Pay", "The pay", true);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		var payCase = new Pay().setDiscordId(event.getUser().getId())
							   .setPrice(event.getOption("Pay").getAsInt())
							   .setDate(new Date());
		repo.save(payCase);
		event.reply("OK").queue();
		
	}

}
