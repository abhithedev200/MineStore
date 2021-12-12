package com.abhiram.minestore.plugincommands;

import com.abhiram.minestore.MineStore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    private final MineStore plugin;

    public MainCommand(){
        this.plugin = MineStore.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(args.length == 0){
            commandSender.sendMessage(ChatColor.DARK_RED + "MINESTORE | Use /ms reload");
            return true;
        }
        if(args[0].equalsIgnoreCase("reload")) {
            plugin.getMainPluginConfig().reload();
            plugin.getMessageConfig().reload();
            plugin.getLogger().warning("Reloaded.");
            commandSender.sendMessage(ChatColor.DARK_GREEN + "MINESTORE | Succesfully reloaded!");
        }

        if(args[0].equalsIgnoreCase("shop")){
            String message = ChatColor.translateAlternateColorCodes('&',this.plugin.getMainPluginConfig().getConfig().getString("Url"));
            commandSender.sendMessage(message);
        }
        return true;
    }
}
