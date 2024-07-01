package cn.newworld.fannewbiegift.manager;

import cn.newworld.fannewbiegift.FanNewbieGift;
import cn.newworld.fannewbiegift.entity.Item;
import cn.newworld.fannewbiegift.entity.NewbieGift;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class DataManager {
    private final FanNewbieGift plugin;
    private final ConfigManager configManager;

    private NewbieGift newbieGift;

    public DataManager(FanNewbieGift plugin) {
        this.plugin = plugin;
        this.configManager = new ConfigManager(plugin);
        if (!loadData()){
            plugin.log("&c数据加载失败！");
        }
    }

    /**
     * 加载所有配置数据
     * @return 如果数据加载成功返回true，否则返回false
     */
    public boolean loadData() {
        newbieGift = configManager.loadNewbieGift();
        if (newbieGift == null) {
            plugin.getLogger().log(Level.SEVERE, "Failed to load all data.");
            return false;
        }


        return true;
    }

    /**
     * 获取新手礼包数据
     * @return 新手礼包数据对象
     */
    public NewbieGift getNewbieGift() {
        return newbieGift;
    }

}
