package com.abhiram.minestore.file;

import com.abhiram.minestore.MineStore;

public class MessageConfig extends AbstractFile {

    public MessageConfig() {
        super(MineStore.getInstance(), "messages.yml", "", true);
    }
}
