package cn.ousky.small.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Store {
    private int store_id;
    private String store_name;
    private String city;
    private String head_portrait;
    private String internal_classification;
    private String store_array_img;
    private List<Commodity> commoditys;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public String getInternal_classification() {
        return internal_classification;
    }

    public void setInternal_classification(String internal_classification) {
        this.internal_classification = internal_classification;
    }

    public String getStore_array_img() {
        return store_array_img;
    }

    public void setStore_array_img(String store_array_img) {
        this.store_array_img = store_array_img;
    }

    public List<Commodity> getCommoditys() {
        return commoditys;
    }

    public void setCommoditys(List<Commodity> commoditys) {
        this.commoditys = commoditys;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("store_id", store_id);
        json.put("store_name", store_name);
        json.put("city", city);
        json.put("head_portrait", head_portrait);
        json.put("commoditys", commoditys);
        if (internal_classification != null) {
            json.put("internal", internal_classification.split(","));
        }
        if (store_array_img != null) {
            json.put("arrayImg", store_array_img.split(","));
        }

        return json.toJSONString();
    }
}
