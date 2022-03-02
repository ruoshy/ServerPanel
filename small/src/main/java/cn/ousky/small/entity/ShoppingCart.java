package cn.ousky.small.entity;

public class ShoppingCart {
    private int shoppingCart_id;
    private int commodity_id;
    private int user_id;
    private int num;

    public int getShoppingCart_id() {
        return shoppingCart_id;
    }

    public void setShoppingCart_id(int shoppingCart_id) {
        this.shoppingCart_id = shoppingCart_id;
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
