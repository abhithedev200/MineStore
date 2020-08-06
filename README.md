# MineStore
An Donation Bridge Plugin


This plugin is for https://www.mc-market.org/resources/13698/

its supports all minestore (PRO,Lite etc).


how can i build this plugin?

run maven package
command:
mvn clean package

# API
minestore api 
Dont like how minestore order handling?
Make your own Addon.

Example minestore Event Handling.



    @EventHandler
    public void minestoreapi(MinestoreAPIEvents e){
   
         // Cancels The minestore order process
         e.SetCanceled(true);
         // Prints the minestore Commands from Website
         System.out.println(e.getCommand());
        
    }

