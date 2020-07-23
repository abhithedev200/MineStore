package com.abhiram.minestore.commands;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.gui.BuyGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuyCommand implements CommandExecutor {
    private MineStore plugin;

    public BuyCommand(MineStore plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            BuyGui gui = new BuyGui(plugin);
            gui.OpenInventory(p);
            return true;
        }

        commandSender.sendMessage("[MineStore]Console cant run this command");
        return true;
    }
}
