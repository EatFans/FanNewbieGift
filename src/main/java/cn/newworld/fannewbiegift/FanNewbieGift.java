package cn.newworld.fannewbiegift;

import cn.newworld.fannewbiegift.listeners.InventoryEventListener;
import cn.newworld.fannewbiegift.manager.ConfigManager;
import cn.newworld.fannewbiegift.manager.DataManager;
import cn.newworld.fannewbiegift.manager.InventoryManager;
import cn.newworld.fannewbiegift.listeners.PlayerEventListener;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public final class FanNewbieGift extends JavaPlugin {
    private InventoryManager inventoryManager;
    private DataManager dataManager;
    private ConfigManager configManager;

    public void log(String message){
        String pluginName = "[FanNewbieGift] ";
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',pluginName+message));
    }

    @Override
    public void onEnable() {
        String version = getDescription().getVersion();
        saveDefaultConfig();
        saveUserListConfig();
        log(" ");
        log("========================================================================");
        log(" ");
        log("&6插件: &eFanNewbieGift");
        log(" ");
        log("&6作者：&eEatFan");
        log(" ");
        log("&6版本：&a"+version);
        log(" ");
        log("========================================================================");
        log(" ");
        log("&c[ 注意 ]: 本插件为私人插件，禁止商业行为，未经作者允许，绝不允许出售、发布！");
        log(" ");
        log("  |\\_/|");
        log(" / @ @ \\");
        log("( > º <)");
        log(" `>>x<<´");
        log(" /  O  \\ ");
        log(" ");
        log("&b插件正在启动中...");

        // 初始化
        configManager = new ConfigManager(this);
        dataManager = new DataManager(this);
        inventoryManager = new InventoryManager(this);

        if (inventoryManager.initInventory()){
            log("&a新手礼包 &b初始化加载成功！");
        } else {
            log("&c新手礼包 初始化加载失败！请检查配置文件或其他地方是否正确！");
        }

        List<String> userList = dataManager.getUserList();
        for (String string : userList){
            log(string);
        }
        boolean reduceUserList = dataManager.reduceUserList("fdhjaskefheuiqe231321321");
        if (reduceUserList)
            log("成功删除用户");
        else
            log("删除用户失败");

        registerEvents();
        log("&a事件监听器 &b注册完毕！");

        registerCommands();
        log("&a指令 &b注册完毕！");

        log("&a插件 &b启动完毕！");
    }



    @Override
    public void onDisable() {
        log("&c插件正在卸载中...");

        List<String> userList = dataManager.getUserList();
        for (String string : userList){
            log(string);
        }

        HandlerList.unregisterAll();
        log("&c插件已经卸载！感谢使用！");
    }

    private void saveUserListConfig(){
        File userListFile = new File(getDataFolder(), "userList.yml");
        if (!userListFile.exists()){
            saveResource("userList.yml",false);
        }
    }

    private void registerCommands() {
        // 注册命令
    }

    private void registerEvents() {
        // 注册事件监听器
        getServer().getPluginManager().registerEvents(new PlayerEventListener(this),this);
        getServer().getPluginManager().registerEvents(new InventoryEventListener(this),this);
    }



    public InventoryManager getInventoryManager(){
        return inventoryManager;
    }

    public ConfigManager getConfigManager(){
        return configManager;
    }

    public DataManager getDataManager(){
        return dataManager;
    }
}
