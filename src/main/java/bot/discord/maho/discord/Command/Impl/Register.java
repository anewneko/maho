//package bot.discord.maho.discord.Command.Impl;
//
//import org.springframework.stereotype.Component;
//
//import bot.discord.maho.discord.Command.Command;
//import lombok.RequiredArgsConstructor;
//import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
//import net.dv8tion.jda.api.interactions.commands.OptionType;
//import net.dv8tion.jda.api.interactions.commands.build.Commands;
//import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
//
//@Component
//@RequiredArgsConstructor
//public class Register implements Command {
//
//	@Override
//	public SlashCommandData setCommands() {
//		return Commands.slash(getCmd(), "註冊用")
//					   .setGuildOnly(true)
//					   .addOption(OptionType.STRING, "firstkey", "The first key for login maho robot's web.", true)
//					   .addOption(OptionType.STRING, "secondkey", "Set second key for web.", true);
//	}
//
//	@Override
//	public void commandAct(SlashCommandInteractionEvent event) {
//		event.getOption("firstkey").getAsString();
//		event.getOption("secondkey").getAsString();
//		event.reply("OK").queue();
//
//	}
//
//}
