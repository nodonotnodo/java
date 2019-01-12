package com.github.nodonotnodo.checkStand;

public interface OrderCenter {

    void addOrder(Order order);

    void removeOrder(Order order);

    String orderTable();

    String orderTable(String name);

    void store();

    void load();
}
