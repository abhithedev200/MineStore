# MineStore
An Donation Bridge Plugin


This plugin is for https://www.mc-market.org/resources/13698/


Now this plugin doesnt support all types of Minestore verion this only supports Minestore V2.2 please use lagacy version from old branch if you want to use this plugin for older version or using in Minestore Lite


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

