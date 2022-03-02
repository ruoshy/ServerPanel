package cn.ousky.small.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class User {
    private int user_id;
    private int store_id;
    private String pwd;
    private String name;
    private String phone;
    private String head_portrait;
    private String address;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("user_id", user_id);
        json.put("store_id", store_id);
        // json.put("pwd", pwd);
        json.put("name", name);
        json.put("phone", phone);
        json.put("head_portrait", head_portrait);
        if (address != null) {
            String[] items = address.split(";");
            JSONArray jsonArray = new JSONArray();
            for (String ls : items) {
                String[] lsItem = ls.split(",");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", lsItem[0]);
                jsonObject.put("phone", lsItem[1]);
                jsonObject.put("provinces", lsItem[2]);
                jsonObject.put("city", lsItem[3]);
                jsonObject.put("address", lsItem[4]);
                jsonArray.add(jsonObject);
            }
            json.put("address", jsonArray);
        }

        return json.toJSONString();
    }
}
