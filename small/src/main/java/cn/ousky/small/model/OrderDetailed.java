package cn.ousky.small.model;

import java.util.List;

public class OrderDetailed {
    private Address address;
    private List<Pay> pay;
    private int user;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Pay> getPay() {
        return pay;
    }

    public void setPay(List<Pay> pay) {
        this.pay = pay;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
