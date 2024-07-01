package cn.newworld.fannewbiegift.listeners;

import cn.newworld.fannewbiegift.FanNewbieGift;
import cn.newworld.fannewbiegift.entity.Item;
import cn.newworld.fannewbiegift.manager.DataManager;
import cn.newworld.fannewbiegift.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryEventListener implements Listener {

    private final FanNewbieGift plugin;
    private final InventoryManager inventoryManager;
    private final DataManager dataManager;
    private boolean newbieGiftInventoryOpenFlag = false;  // 新手礼包容器打开标识

    private final Inventory newbieGift;


    public InventoryEventListener(FanNewbieGift plugin){
        this.plugin = plugin;
        this.inventoryManager = plugin.getInventoryManager();
        this.dataManager = plugin.getDataManager();
        this.newbieGift = inventoryManager.getInventory("newbieGift");

    }

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event){
        Player player = (Player) event.getPlayer();
        Inventory openedInventory = event.getInventory();
        if (openedInventory.equals(newbieGift)){
            player.sendMessage(ChatColor.RED + "新手礼包已经打开！");
            newbieGiftInventoryOpenFlag = true;
        }
    }


    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        Inventory closedInventory = event.getInventory();
        if (closedInventory.equals(newbieGift)){
            player.sendMessage(ChatColor.RED+"新手礼包已经关闭！");
            newbieGiftInventoryOpenFlag = false;
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack currentItem = event.getCurrentItem();
        // 检查newbieGift容器是否已经打开
        if (newbieGiftInventoryOpenFlag){
            // 如果点击的不是容器部分，空白部分，就直接return掉
            if (clickedInventory == null){
                return;
            }
            // 如果玩家点击的是自己物品栏，同样取消点击
            if (clickedInventory.getType() == InventoryType.PLAYER){
                event.setCancelled(true);
            }
            // 如果玩家点击的是新手礼包部分，取消点击
            if (clickedInventory.equals(newbieGift)){
                event.setCancelled(true);

                clickItem(player,currentItem);
                player.closeInventory();
            }
        }

    }

    private void clickItem(Player player, ItemStack clickedItem){
        List<ItemStack> items = inventoryManager.getItems("newbieGift");
        List<Item> itemsData = dataManager.getNewbieGift().getItems();

        for (ItemStack item : items){
            if (item.equals(clickedItem)){
                // 检查被点击的物品信息是否和存储在配置文件的物品信息是否相同
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta == null) {
                    continue;
                }
                String itemName = itemMeta.getDisplayName();
                Material itemType = item.getType();

                for (Item itemData : itemsData){
                    if (itemName.equalsIgnoreCase(itemData.getName())){
                        if (itemType.equals(itemData.getMaterial())){
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BELL,1.0f,1.0f);

                            executeCommandAsConsole(itemData.getCommands());

                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f&l[ &e提示 &f&l] &6成功领取 &a"+itemName+" &6!"));
                        }
                    }
                }


            }
        }
    }

    private void executeCommandAsConsole(List<String> commands) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        for (String command : commands){
            Bukkit.dispatchCommand(console, command);
        }

    }
}
