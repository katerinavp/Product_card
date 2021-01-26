package com.example.test_lm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductAction {
    HashMap<String, TreeMap<Integer, Integer>> numberShopMap;
    Map<Integer, Integer> dataShop;
    int numberShop;
    int countProduct;

    public String getNameProduct(Product product) {

        List<String> nameProducts = product.displayedName.displayedName.value;
        for (String name : nameProducts) {
            return name;
        }
        return null;
    }

    public String getNumberShop(Product product) {
        numberShopMap = product.stock.stocks;
        dataShop = new TreeMap<>();

        StringBuilder numberBuilder = new StringBuilder();
        for (Map.Entry<String, TreeMap<Integer, Integer>> entryHash : numberShopMap.entrySet()) {
            dataShop = entryHash.getValue();
            System.out.println("Коллекция  " + dataShop);
        }
        for (Map.Entry<Integer, Integer> entryTree : dataShop.entrySet()) {
            numberShop = entryTree.getKey();
            countProduct = entryTree.getValue();
            System.out.println("Коллекция MAP " + numberShop + " " + countProduct);
            if (countProduct != 0) {
                numberBuilder.append(numberShop);
                numberBuilder.append(";");
            }
        }
        return numberBuilder.toString();
    }

    public String maxCount() {
        int maxValue = dataShop.values().stream().max(Integer::compare).orElse(null);
        int keyMaxValue = 0;
        for (Map.Entry<Integer, Integer> entryTree : dataShop.entrySet()) {
            if (maxValue == entryTree.getValue()) {
                keyMaxValue = entryTree.getKey();
            }
        }

        return "Максимальное количество товара - " + maxValue + ", номер магазина - " + keyMaxValue;
    }
}
