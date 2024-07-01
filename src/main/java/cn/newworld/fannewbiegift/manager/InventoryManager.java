package cn.newworld.fannewbiegift.manager;

import cn.newworld.fannewbiegift.FanNewbieGift;
import cn.newworld.fannewbiegift.entity.Item;
import cn.newworld.fannewbiegift.entity.NewbieGift;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 容器管理者
 */
public class InventoryManager {
    private final Map<String, Inventory> inventories = new HashMap<>();

    private final FanNewbieGift plugin;
    private final DataManager dataManager;

    public InventoryManager(FanNewbieGift plugin) {
        this.plugin = plugin;
        this.dataManager = new DataManager(plugin);
    }

    public boolean initInventory() {
        if (!dataManager.loadData()) {
            return false;
        }

        NewbieGift newbieGift = dataManager.getNewbieGift();
        Inventory newbieGiftInventory = createNewbieGiftInventory(newbieGift);

        if (newbieGiftInventory == null) {
            return false;
        }

        inventories.put("newbieGift", newbieGiftInventory);
        return true;
    }

    /**
     * 创建一个新手礼包容器
     *
     * @param newbieGift 新手礼包数据
     * @return 创建成功就返回该容器，否则就返回null
     */
    private Inventory createNewbieGiftInventory(NewbieGift newbieGift) {
        String title = newbieGift.getTitle();
        int size = newbieGift.getSize();
        List<Item> items = newbieGift.getItems();

        Inventory newbieGiftInventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));

        for (Item item : items) {
            ItemStack itemStack = new ItemStack(item.getMaterial(), item.getAmount());
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemMeta != null) {
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', item.getName()));
                List<String> lore = item.getLore();
                if (lore != null && !lore.isEmpty()) {
                    List<String> coloredLore = new ArrayList<>();
                    for (String line : lore) {
                        coloredLore.add(ChatColor.translateAlternateColorCodes('&', line));
                    }
                    itemMeta.setLore(coloredLore);
                }
                itemStack.setItemMeta(itemMeta);
                newbieGiftInventory.setItem(item.getSlot(), itemStack);
            }
        }

        return newbieGiftInventory;
    }


    public Inventory getInventory(String inventoryName) {
        return inventories.get(inventoryName);
    }

}
