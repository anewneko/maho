package bot.discord.maho.discord.Command.Impl;

import org.springframework.stereotype.Component;

import bot.discord.maho.discord.Command.Command;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

@Component
@RequiredArgsConstructor
public class InterviewCommit implements Command  {
	private final static String cmd = "提交";
	private final static 
	String description ="""
						請在此頻道貼上您的【個人資料卡】、【角色池】、【未解放角色】、【上個月最後一天分數】\r\n
						完成後提交"
						""";  
	
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, description);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		System.out.println("提交");
		event.reply("OK").queue();
		
	}
	
	@Override
	public String getCmd() {
		return cmd;
	}

}
