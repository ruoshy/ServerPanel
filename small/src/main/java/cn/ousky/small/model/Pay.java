package cn.ousky.small.model;

import java.util.List;

public class Pay {
    private int freight;
    private float price;
    private String shop;
    private List<Baby> baby;

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public List<Baby> getBaby() {
        return baby;
    }

    public void setBaby(List<Baby> baby) {
        this.baby = baby;
    }
}
