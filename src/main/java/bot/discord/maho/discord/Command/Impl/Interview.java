package bot.discord.maho.discord.Command.Impl;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
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
	
	private final RedisTemplate<String,String> redisTemplate;
	
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, description);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		event.reply("請在此頻道貼上您的【個人資料卡】、【角色池】、【未解放角色】、【上個月最後一天分數】\r\n完成後輸入 \"/提交\"").queue();
		var id = event.getChannel().getLatestMessageId();
		
		redisTemplate.opsForValue()
					 .set(event.getMember().getId(), id, 10L, TimeUnit.MINUTES);
	}
	
	@Override
	public String getCmd() {
		return cmd;
	}

}
