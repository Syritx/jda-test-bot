package jdaTestBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	
	String[] commands = {
		"cmds - Lists commands",
		"info - Gives generic info about me",
		"kick - kicks a user",
		"ban - bans a user <arg1> for <arg2> amount"
	};
	
	String prefix, permissionMessage = "you do not have permission to use that command";
	public Commands(String prefix) {
		this.prefix = prefix;
	}
	
	EmbedBuilder createEmbed(String title, String description, String[] field) {
		EmbedBuilder info = new EmbedBuilder();
		info.setTitle(title);
		info.setDescription(description);
		info.addField(field[0],field[1], true);
		info.setColor(0xf45642);
		
		return info;
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String message = event.getMessage().getContentRaw();
		
		if (event.getAuthor().isBot()) return;
		
		// General commands
		if (message.equalsIgnoreCase(prefix+"info")) {
			
			event.getChannel().sendTyping().queue();
			EmbedBuilder info = createEmbed("JDA BOT","Just a test bot made with JDA",new String[] {"Creator","Syritx"});
			
			event.getChannel().sendMessage(info.build()).queue();
		}
		
		if (message.equalsIgnoreCase(prefix+"cmds")) {
			String cmdList = "";
			
			for (String cmd : commands) {
				cmdList += cmd+"\n";
			}
			
			EmbedBuilder commands = createEmbed("COMMAND LIST",cmdList+"make sure to add the prefix at the start of the commands",new String[] {"Creator","Syritx"});
			event.getChannel().sendMessage(commands.build()).queue();
		}
		
		//------------------------------//
		// STAFF COMMANDS //
		//------------------------------//
		
		if (message.startsWith(prefix+"kick")) {
			if (event.getMessage().getMentionedMembers().isEmpty()) return;
			
			Member self = event.getGuild().getSelfMember();
			Member target = (Member)event.getMessage().getMentionedUsers().get(0);
			
			if (!self.hasPermission(Permission.KICK_MEMBERS)) {
				event.getChannel().sendMessage(permissionMessage);
				return;
			}
			
			try {
				event.getGuild().kick(target,"hi")
					 .reason("you have been kicked from the server").queue();
			}catch(Exception e) {}
		}
		
		if (message.startsWith(prefix+"ban") && message.split(" ").length == 3) {
			if (event.getMessage().getMentionedMembers().isEmpty()) return;
			
			Member self = event.getGuild().getSelfMember();
			Member target = (Member)event.getMessage().getMentionedUsers().get(0);
			
			if (!self.hasPermission(Permission.BAN_MEMBERS)) {
				event.getChannel().sendMessage(permissionMessage);
				return;
			}
			
			try {
				event.getGuild().ban(target, Integer.parseInt(message.split(" ")[2]))
					 .reason("you have been banned from the server").queue();
			}catch(Exception e) {}
		}
	}
}
