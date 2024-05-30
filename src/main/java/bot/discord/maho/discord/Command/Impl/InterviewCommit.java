package bot.discord.maho.discord.Command.Impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import bot.discord.maho.discord.Command.Command;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.utils.FileUpload;

@Component
@RequiredArgsConstructor
public class InterviewCommit implements Command  {
	private final static String cmd = "提交";
	private final static 
	String description ="提交後請靜待管理員審核。";
	private final static 
	String manger_msg ="%s %s 已經提交面試資料。";
	
	private final static
	String wait_msg = "%s 您已完成資料提交，請在此頻道等待管理員提問";
	private final RedisTemplate<String,String> redisTemplate;
	
	@Override
	public SlashCommandData setCommands() {
		return Commands.slash(cmd, description);
	}

	@Override
	public void commandAct(SlashCommandInteractionEvent event) {
		try {
			var startMsgId = redisTemplate.opsForValue()
										  .getAndDelete(event.getMember().getId());
			
			var list = new ArrayList<>(event.getChannel().getHistoryAfter(startMsgId, 100).complete().getRetrievedHistory());
			Collections.reverse(list);
			
			event.deferReply()
				 .queue();
			
			list.stream()
				.skip(1)
				.forEach(el -> moveAndClearMsg(event, el));
			
			event.getHook().sendMessage("已經提交面試資料，請等待管理員審核。").queue();
			
			event.getGuild()
				 .getTextChannelById(659405580661817354L)
				 .sendMessage(String.format(manger_msg, 
						 					event.getGuild().getRoleById(618826260071710750L).getAsMention(), 
						 					event.getMember().getEffectiveName()))
				 .queue();
			
			event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(683988941271334928L)).queue();
			
			event.getGuild()
				 .getTextChannelById(683988724593590272L)
				 .sendMessage(String.format(wait_msg, 
						 					event.getMember().getAsMention()))
				 .queue();
		} catch (IllegalArgumentException e) {
			event.reply("請先使用 /面試 指令開始面試。").queue();
		}
	}
	
	@Override
	public String getCmd() {
		return cmd;
	}
	
	
	private void moveAndClearMsg(SlashCommandInteractionEvent event, Message el) {
		var files = el.getAttachments()
					  .stream()
					  .map(att -> {
						  var tmp = new RestTemplate();
						  var res = tmp.getForEntity(att.getUrl(), byte[].class);
						  var in = new ByteArrayInputStream(res.getBody());
						  return FileUpload.fromData(in, att.getFileName());
					  })
					  .collect(Collectors.toList());
		
		event.getGuild()
			 .getTextChannelById(659405580661817354L)
			 .sendMessage(el.getContentDisplay())
			 .addFiles(files)
			 .queue();
		
		event.getChannel()
			 .deleteMessageById(el.getId())
			 .queue();
	}

}
