package jdaTestBot;
import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
	
	static JDA jda;
	static Commands cmds = new Commands(BotConfig.prefix);
	
	public static void main(String[] args) throws LoginException {
		
		jda = new JDABuilder(AccountType.BOT).setToken(BotConfig.token).build();
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		//jda.getPresence().setActivity(Activity.playing("Online"));
		
		jda.addEventListener(cmds);
		System.out.println("ADDED COMMANDS");
	}
}
