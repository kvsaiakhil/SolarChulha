package com.example.akhil.solarchulha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baoyz.swipemenulistview.SwipeMenuListView;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences dishPreference;
    public static String DISH_SELECTED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button vesselDishes = findViewById(R.id.vessel_dishes);
        Button kadaiDishes = findViewById(R.id.kadai_dishes);
        Button panDishes = findViewById(R.id.pan_dishes);
        Button cookerDishes = findViewById(R.id.cooker_dishes);
        dishPreference = getSharedPreferences("Dish preferences", MODE_PRIVATE);

        SwipeMenuListView listView1 = findViewById(R.id.vessel_item);
        SwipeMenuListView listView2 = findViewById(R.id.kadai_item);
        SwipeMenuListView listView3 = findViewById(R.id.pan_item);
        SwipeMenuListView listView4 = findViewById(R.id.cooker_item);

        vesselDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DishList.class);
                dishPreference.edit().putString(DISH_SELECTED, "vessel").apply();
                startActivity(intent);
            }
        });

        kadaiDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DishList.class);
                dishPreference.edit().putString(DISH_SELECTED, "kadai").apply();
                startActivity(intent);
            }
        });

        panDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DishList.class);
                dishPreference.edit().putString(DISH_SELECTED, "pan").apply();
                startActivity(intent);
            }
        });

        cookerDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DishList.class);
                dishPreference.edit().putString(DISH_SELECTED, "cooker").apply();
                startActivity(intent);
            }
        });


    }
}
