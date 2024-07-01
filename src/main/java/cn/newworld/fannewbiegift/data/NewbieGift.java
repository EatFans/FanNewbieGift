package cn.newworld.fannewbiegift.data;

import java.util.List;

public class NewbieGift {
    private String title;
    private int size;
    private List<Item> items;

    public NewbieGift(String title, int size, List<Item> items) {
        this.title = title;
        this.size = size;
        this.items = items;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setSize(int size){
        this.size = size;
    }

    public int getSize(){
        return size;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public List<Item> getItems(){
        return items;
    }
}
