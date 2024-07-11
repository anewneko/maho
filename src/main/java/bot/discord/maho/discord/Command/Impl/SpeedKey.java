package bot.discord.maho.discord.Command.Impl;

import org.springframework.stereotype.Component;

import bot.discord.maho.database.CrudService.Impl.LoginDetailService;
import bot.discord.maho.database.CrudService.Impl.MemberService;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.database.Entity.LoginDetail;
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
	private final MemberService userSvc;
	private final LoginDetailService spKeySvc;
	
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, description);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		var dcUser = event.getUser();
		var member = userSvc.findByDiscordId(dcUser.getIdLong());
		if (member == null) {
			member = userSvc.save(Member.of(dcUser));
		}
		
		var spKey = LoginDetail.getCurrent()
							   .setMember(member);
		
		spKeySvc.save(spKey);
		event.reply("取得一次性鑰匙 : `" + spKey.getId() +"`\n請於 10 分鐘內登入").setEphemeral(true).queue();
	}
	
	@Override
	public String getCmd() {
		return cmd;
	}

}
