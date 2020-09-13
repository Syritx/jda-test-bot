package jdaTestBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	
	String prefix;
	public Commands(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] messageArgs = event.getMessage().getContentRaw().split(" ");
		System.out.println("MESSAGE SENT");
		
		if (messageArgs[0] == prefix) {
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("JDA BOT");
			info.setDescription("just some test jda bot");
			info.addField("Creator","Syritx", false);
			info.setColor(0xf45642);
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
		}
	}
}
