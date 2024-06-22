package bot.discord.maho.discord.Command.Impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.database.Repository.MemberRepository;
import bot.discord.maho.discord.Command.Command;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

@Component
@RequiredArgsConstructor
public class SpeedKey  implements Command {
	private final static String cmd = "key";
	private final static String description = "拿到登入Maho Web的一次性鑰匙";
	private final MemberRepository memberRepo;
	
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, description);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		var key = UUID.randomUUID().toString();
		var dcUser = event.getUser();
		var member = memberRepo.findByDiscordId(dcUser.getIdLong());
		if (member == null) 
			member = Member.of(dcUser);
		
		member.setSpeedkey(key);
		memberRepo.save(member);
		event.reply("取得key性鑰匙 : " + key).setEphemeral(true).queue();
	}
	
	@Override
	public String getCmd() {
		return cmd;
	}

}
