package com.github.nodonotnodo.checkStand;

public interface GoodsCenter {

    void addGoods(Goods good);

    void removeGoods(Goods good);

    void updateGoods(Goods good);

    boolean isExistGoods(String name);

    Goods getGoods(String name);

    String listGoods();

    void store();

    void load();
}
