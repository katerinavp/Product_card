package com.example.test_lm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    MyViewModel model;
    private TextView nameProduct;
    private TextView numberShop;
    private TextView maxCount;
    private final String URL = "https://raw.githubusercontent.com/katerinavp/JSON_LMv3/master/product.json";
    private final OkHttpClient client = new OkHttpClient();
    private final Request request = new Request.Builder()
            .url(URL)
            .build();
    @Nullable
    private Call call = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        model = ViewModelProviders.of(this).get(MyViewModel.class);
        String[] list = model.getData();
        if (list != null) {
            nameProduct.setText(list[0]);
            numberShop.setText(list[1]);
            maxCount.setText(list[2]);

        }
        setOnClickButton();
    }

    private void init() {
        nameProduct = findViewById(R.id.nameProduct);
        numberShop = findViewById(R.id.numberShop);
        maxCount = findViewById(R.id.maxCount);

    }

    private void setOnClickButton() {
        findViewById(R.id.btnOK).setOnClickListener(v -> run());
    }


    void run() {

        prepareCall().enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(MainActivity.this,
                        "Ошибка сети",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = Objects.requireNonNull(response.body()).string();

                    final Product product = new Gson().fromJson(myResponse, Product.class);
                    final ProductAction productAction = new ProductAction();
                    final String nameProduct = productAction.getNameProduct(product);
                    final String numberShop = productAction.getNumberShop(product);
                    final String maxCount = productAction.maxCount();
                    MainActivity.this.runOnUiThread(() -> {
                        MainActivity.this.nameProduct.setText(nameProduct);// Вывод названия товара
                        MainActivity.this.numberShop.setText(numberShop); // вывод массива номеров магазинов, в которых есть товары в наличии
                        MainActivity.this.maxCount.setText(maxCount);      //max кол-во товара и номер магазина
                        model.setInitData(nameProduct, numberShop, maxCount);

                    });
                }
            }
        });
    }

    private Call prepareCall() {
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
        call = client.newCall(request);
        return call;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }
}