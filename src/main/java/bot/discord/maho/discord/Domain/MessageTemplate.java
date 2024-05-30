package bot.discord.maho.discord.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Message;
@Getter
public class MessageTemplate {
	private String content = "";
	private List<String> urls = new ArrayList<>();
	
	private MessageTemplate() {}
	
	private MessageTemplate(String content) {
		this.content = content;
	}
	
	
	public MessageTemplate setContent(String content) {
		this.content = content;
		return this;
	}
	public MessageTemplate setUrls(List<String> urls) {
		this.urls = urls;
		return this;
	}
	
	public MessageTemplate addUrl(String url) {
		urls.add(url);
		return this;
	}
	
	public MessageTemplate addUrls(List<String> urls) {
		this.urls.addAll(urls);
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("%s \n\n %s", content , String.join("\n", urls));
	}
	
	public static MessageTemplate of(String content) {
		return new MessageTemplate(content);
	}
	
	public static MessageTemplate of(Message msg) {
		var urls = msg.getAttachments().stream().map(el -> el.getProxyUrl()).collect(Collectors.toList());
		return new MessageTemplate(msg.getContentRaw()).addUrls(urls);
	}
	

}
