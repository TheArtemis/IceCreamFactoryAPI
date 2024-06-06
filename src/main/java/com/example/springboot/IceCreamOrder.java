package com.example.springboot;

public class IceCreamOrder {
    private int id;
    private String flavour1;
    private String flavour2;
    private String flavour3;

    IceCreamOrder() {}
    IceCreamOrder(String flavour1, String flavour2, String flavour3) {
        this.flavour1 = flavour1;
        this.flavour2 = flavour2;
        this.flavour3 = flavour3;
    }
    public int getId() {
        return id;
    }


}

