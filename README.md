# MineStore
An Donation Bridge Plugin


This plugin is for https://www.mc-market.org/resources/13698/

This plugin supports all minestore types (PRO,LITE,ETC).


How do I build this project?

```
mvn clean package
```

# API
MineStore API 
Don't like how MineStore is handling orders?
Create your own addon to our plugin!

Example...



    @EventHandler
    public void onMinestoreApi(MinestoreAPIEvents e){
   
         // Cancels The minestore order process
         e.SetCanceled(true);
         // Prints the minestore Commands from Website
         System.out.println(e.getCommand());
        
    }

