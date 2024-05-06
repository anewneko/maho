package bot.discord.maho.discord.Command.Impl;


import java.util.Date;

import org.springframework.stereotype.Component;

import bot.discord.maho.database.Entity.Salary;
import bot.discord.maho.database.Repository.SalaryRepository;
import bot.discord.maho.discord.Command.Command;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
@Component
@RequiredArgsConstructor
public class BookkingSalary implements Command{
	final private SalaryRepository repo;
	
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(getCmd(), "記帳用")
					   .setGuildOnly(true)
					   .addOption(OptionType.INTEGER, "pay", "The pay", true);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		var payCase = new Salary().setDiscordId(event.getUser().getId())
							   .setPrice(event.getOption("Pay").getAsInt())
							   .setGivenDate(new Date());
		repo.save(payCase);
		event.reply("OK").queue();
		
	}

}
