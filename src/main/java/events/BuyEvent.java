package events;

import com.abhiram.minestore.MineStore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BuyEvent implements Listener {
    private MineStore plugin;

    public BuyEvent(MineStore plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onInventoryOpen(InventoryClickEvent e){
        String inventory_name = ChatColor.translateAlternateColorCodes('&',plugin.buy.getConfig().getString("GUI-settings.Gui-name"));

        if(e.getView().getTitle().contains(inventory_name)){
            e.setCancelled(true);
        }else{
            plugin.getLogger().warning("Invalid name");
        }
    }
}
