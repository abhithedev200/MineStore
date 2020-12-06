package com.abhiram.minestore.task;

import com.abhiram.minestore.managers.CommandManager;

public class CommandRunnerTask implements Runnable {


    @Override
    public void run() {
        CommandManager.getCommandManager().runAliveCommands();
    }
}
