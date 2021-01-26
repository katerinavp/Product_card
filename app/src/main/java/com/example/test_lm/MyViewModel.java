package com.example.test_lm;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private String[] list;

    public void setInitData(String name, String number, String max) {
        list = new String[]{name, number, max};

    }

    public  String[] getData(){
        if(list == null){
            return null;
        }
        return list;
    }
}
