package cn.newworld.fannewbiegift.listeners;

import cn.newworld.fannewbiegift.FanNewbieGift;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

public class PlayerEventListener implements Listener {
    private final FanNewbieGift plugin;

    public PlayerEventListener(FanNewbieGift plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        // 延迟打开背包，等待服务器加载完成
        Bukkit.getScheduler().runTask(plugin, () -> {
            Inventory newbieGift = plugin.getInventoryManager().getInventory("newbieGift");
            if (newbieGift != null) {
                player.openInventory(newbieGift);
            }
        });

    }
}
