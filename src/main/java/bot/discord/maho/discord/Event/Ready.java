package bot.discord.maho.discord.Event;

import org.springframework.stereotype.Component;

import jakarta.annotation.Nonnull;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class Ready extends  ListenerAdapter  {
	@Override
	public void onReady(@Nonnull ReadyEvent event) {
		TextChannel channel = event.getJDA().getTextChannelById(685396765679157258L);
		channel.sendMessage("咕嚕靈波（●′∀‵）ノ♡").queue();
	}
}
