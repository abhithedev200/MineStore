# Official MineStore Plugin


This plugin is for https://www.mc-market.org/resources/13698/


Now this plugin doesn't support all types of MineStore. It works only with Minestore V2.2^ and higher. Please, use legacy versions from old branch, if you want to use plugin for older versions or MineStore Lite :)


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

