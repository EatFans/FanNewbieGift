package cn.newworld.fannewbiegift.manager;

import cn.newworld.fannewbiegift.FanNewbieGift;
import cn.newworld.fannewbiegift.data.Item;
import cn.newworld.fannewbiegift.data.NewbieGift;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class ConfigManager {
    private final FanNewbieGift plugin;
    private final FileConfiguration config;

    public ConfigManager(FanNewbieGift plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public NewbieGift loadNewbieGift() {
        String title = config.getString("newbieGift.title");
        int size = config.getInt("newbieGift.size");
        List<Map<?, ?>> items = config.getMapList("newbieGift.items");

        if (title == null || size == 0 || items == null) {
            plugin.getLogger().log(Level.WARNING, "Failed to load newbieGift configuration. Check configuration file.");
            return null;
        }

        List<Item> itemList = getItems(items);
        if (itemList == null) {
            plugin.getLogger().log(Level.WARNING, "Failed to load newbieGift items. Check configuration file.");
            return null;
        }

        return new NewbieGift(title, size, itemList);
    }

    private List<Item> getItems(List<Map<?, ?>> items) {
        List<Item> itemList = new ArrayList<>();

        for (Map<?, ?> item : items) {
            String name = (String) item.get("name");
            Material material = Material.getMaterial((String) item.get("material"));
            int amount = (Integer) item.get("amount");
            int slot = (Integer) item.get("slot");
            List<String> lore = (List<String>) item.get("lore");
            List<String> commands = (List<String>) item.get("commands");

            if (name == null || material == null || amount > 64 || slot >= 54 || lore == null || commands == null) {
                plugin.getLogger().log(Level.WARNING, "Invalid item configuration: " + item);
                return null;
            }

            Item itemData = new Item(name, material, amount, slot, lore, commands);
            itemList.add(itemData);
        }

        return itemList;
    }
}
