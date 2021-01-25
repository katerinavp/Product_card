package com.example.test_lm;

import java.util.HashMap;
import java.util.TreeMap;

public class Stock {

    HashMap<String, TreeMap<Integer, Integer>> stocks;

    public Stock(HashMap<String, TreeMap<Integer, Integer>> stocks) {
        this.stocks = stocks;
    }
}
