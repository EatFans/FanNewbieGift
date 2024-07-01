package cn.newworld.fannewbiegift.data;

import org.bukkit.Material;

import java.util.List;

public class Item {
    private String name;
    private Material material;
    private int amount;
    private int slot;
    private List<String> lore;
    private List<String> commands;

    public Item(String name, Material material, int amount, int slot, List<String> lore, List<String> commands) {
        this.name = name;
        this.material = material;
        this.amount = amount;
        this.slot = slot;
        this.lore = lore;
        this.commands = commands;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setMaterial(Material material){
        this.material = material;
    }

    public Material getMaterial(){
        return material;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    public void setSlot(int slot){
        this.slot =slot;
    }

    public int getSlot(){
        return slot;
    }

    public void setLore(List<String> lore){
        this.lore = lore;
    }

    public List<String> getLore(){
        return lore;
    }

    public void setCommands(List<String> commands){
        this.commands = commands;
    }

    public List<String> getCommands(){
        return commands;
    }
}
