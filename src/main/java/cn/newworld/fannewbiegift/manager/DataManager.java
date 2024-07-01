package cn.newworld.fannewbiegift.manager;

import cn.newworld.fannewbiegift.FanNewbieGift;
import cn.newworld.fannewbiegift.entity.NewbieGift;

import java.util.logging.Level;

public class DataManager {
    private final FanNewbieGift plugin;
    private final ConfigManager configManager;

    private NewbieGift newbieGift;

    public DataManager(FanNewbieGift plugin) {
        this.plugin = plugin;
        this.configManager = new ConfigManager(plugin);
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

        // You can add more data loading here if needed

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
