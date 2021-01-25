package com.example.test_lm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView nameProduct;
    private TextView numberShop;
    private TextView maxCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setOnClickButton();

    }

    public void init() {
        nameProduct = findViewById(R.id.nameProduct);
        numberShop = findViewById(R.id.numberShop);
        maxCount = findViewById(R.id.maxCount);

    }

    public void setOnClickButton() {
        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    void run() throws IOException {
        final String url = "https://raw.githubusercontent.com/katerinavp/JSON_LMv3/master/product.json";
        OkHttpClient client = new OkHttpClient();
        // GET request
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    System.out.println("json " + myResponse);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Product product = new Gson().fromJson(myResponse, Product.class);
                            ProductAction productAction = new ProductAction();
                            nameProduct.setText(productAction.getNameProduct(product));// Вывод названия товара
                            numberShop.setText(productAction.getNumberShop(product));// вывод массива номеров магазинов, в которых есть товары в наличии
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                maxCount.setText(productAction.maxCount());      //max кол-во товара и номер магазина
                            }
                        }
                    });
                }
            }
        });
    }
}