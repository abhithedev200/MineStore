package com.abhiram.minestore.commands;

import com.abhiram.minestore.MineStore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShopLinkCommand implements CommandExecutor {


    private final MineStore plugin;

    public ShopLinkCommand(MineStore plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String message = ChatColor.translateAlternateColorCodes('&',this.plugin.config.getConfig().getString("Url"));
        commandSender.sendMessage(message);
        return true;
    }
}
