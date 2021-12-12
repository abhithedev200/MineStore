package com.abhiram.minestore.file;

import com.abhiram.minestore.MineStore;

public class Config extends AbstractFile{

    public Config(){
        super(MineStore.getInstance(),"config.yml","",true);
    }
}
