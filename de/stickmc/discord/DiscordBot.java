package de.stickmc.discord;

import de.stickmc.discord.commands.*;
import de.stickmc.ms.utils.MyData;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public static JDA jda;

    public static void startBot() {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(MyData.discordToken).setActivity(Activity.playing(MyData.game)).build();
            jda.addEventListener(new CMD_Online());
            jda.addEventListener(new CMD_Info());
            jda.addEventListener(new CMD_Regeln());
            jda.addEventListener(new CMD_Clear());
            jda.addEventListener(new CMD_Help());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static void stopBot(){
        jda.shutdown();
    }

}
