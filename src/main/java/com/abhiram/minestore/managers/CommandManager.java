package com.abhiram.minestore.managers;

import com.abhiram.minestore.websocket.jsonobjects.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

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
            for(Command command : waiting_commands)
            {
                if(player.getName().equalsIgnoreCase(command.getUsername()))
                {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command.getCommand());
                    waiting_commands.remove(command);
                }
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
