package com.github.franciscovenned.franciscogender.utils;

import com.github.franciscovenned.franciscogender.Gender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TextUtils {
    public static void send(CommandSender sender, String message){
        Gender plugin = Gender.getInstance();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("prefix") + " " + message));
    }
}
