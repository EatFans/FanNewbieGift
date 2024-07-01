package cn.newworld.fannewbiegift.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryEventListener implements Listener {

    public InventoryEventListener(){

    }

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event){
        HumanEntity player = event.getPlayer();
    }
}
