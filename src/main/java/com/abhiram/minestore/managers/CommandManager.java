package com.abhiram.minestore.managers;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.websocket.jsonobjects.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

public final class CommandManager {
    private static CommandManager instance;
    private ArrayList<Command> waiting_commands = new ArrayList<Command>();

    public void addCommand(Command command)
    {
        waiting_commands.add(command);
    }

    public void runAliveCommands()
    {
        for(Player player : Bukkit.getOnlinePlayers())
        {
            Command com = null;
            for(Command command : waiting_commands)
            {
                if(player.getName().equalsIgnoreCase(command.getUsername())) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.getCommand());
                    com = command;
                }
            }
            if(com != null) {
                waiting_commands.remove(com);
            }
        }
    }

    public static CommandManager getCommandManager()
    {
        if(instance == null)
        {
            instance = new CommandManager();
        }

        return instance;
    }
}
