package cn.ousky.small.entity;

import com.alibaba.fastjson.JSONObject;


public class Commodity {
    private int commodity_id;
    private int store_id;
    private String Commodity_Name;
    private String smallName;
    private String classify;
    private float one_price;
    private int stock;
    private int sales_volume;
    private String img_url;
    private String user_evaluate;
    private float postage;
    private String attribute;
    private String Internal_Classification_Name;
    private Store store;


    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getCommodity_Name() {
        return Commodity_Name;
    }

    public void setCommodity_Name(String commodity_Name) {
        Commodity_Name = commodity_Name;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public float getOne_price() {
        return one_price;
    }

    public void setOne_price(float one_price) {
        this.one_price = one_price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(int sales_volume) {
        this.sales_volume = sales_volume;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getUser_evaluate() {
        return user_evaluate;
    }

    public void setUser_evaluate(String user_evaluate) {
        this.user_evaluate = user_evaluate;
    }

    public float getPostage() {
        return postage;
    }

    public void setPostage(float postage) {
        this.postage = postage;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getInternal_Classification_Name() {
        return Internal_Classification_Name;
    }

    public void setInternal_Classification_Name(String internal_Classification_Name) {
        Internal_Classification_Name = internal_Classification_Name;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("commodity_id", commodity_id);
        json.put("store_id", store_id);
        json.put("Commodity_Name", Commodity_Name);
        json.put("smallName", smallName);
        json.put("classify", classify);
        json.put("one_price", one_price);
        json.put("stock", stock);
        json.put("sales_volume", sales_volume);
        json.put("user_evaluate", user_evaluate);
        json.put("postage", postage);
        json.put("store", store);
        json.put("attribute", attribute);

        if (attribute != null) {
            json.put("attribute", attribute.split(","));

        }
        if (img_url != null) {
            json.put("imgUrl", img_url.split(","));
        }
        return json.toJSONString();
    }

}
