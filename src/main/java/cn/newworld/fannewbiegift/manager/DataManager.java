package cn.newworld.fannewbiegift.manager;

import cn.newworld.fannewbiegift.FanNewbieGift;
import cn.newworld.fannewbiegift.entity.NewbieGift;

import java.util.List;
import java.util.logging.Level;

public class DataManager {
    private final FanNewbieGift plugin;
    private final ConfigManager configManager;

    private NewbieGift newbieGift;
    private List<String> userList;

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
            plugin.getLogger().log(Level.SEVERE, "failed to load newbie gift config data.");
            return false;
        }

        userList = configManager.loadUserList();
        if (userList == null){
            plugin.getLogger().log(Level.SEVERE, "Failed to load user list config data");
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


    /**
     * 获取用户列表
     * @return 返回用户列表
     */
    public List<String> getUserList(){
        return userList;
    }

    /**
     * 重新加载用户列表
     */
    public void reloadUserList() {
        this.userList = configManager.loadUserList();
    }

    /**
     * 添加用户
     * @param userUUID 用户Uuid
     * @return 如果添加成功就返回true，添加失败返回false，如果已经有该用户同样也会添加失败
     */
    public boolean addUserList(String userUUID) {
        if (!userList.contains(userUUID)){
            userList.add(userUUID);
            configManager.saveUserList(userList);
            return true;
        } else {
            return false;
        }

    }
}
