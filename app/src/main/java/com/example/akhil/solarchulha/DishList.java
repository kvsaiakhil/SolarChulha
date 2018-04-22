package com.example.akhil.solarchulha;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class DishList extends AppCompatActivity {

    private static final String TAG = "DishList";

    ArrayList<String> list;
    //ArrayList<Dish> dlist;
    //ArrayList<Dish> dlist = new ArrayList<Dish>();
    ArrayAdapter<String> adapter;
    //DishListAdapter dadapter;
    String item;
    String[] simpleArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        loadData();

        SwipeMenuListView listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter(DishList.this, android.R.layout.simple_list_item_1, list);
        //dadapter = new DishListAdapter(DishList.this, R.layout.adapter_view_layout, dlist);

        listView.setAdapter(adapter);



        Button add = findViewById(R.id.add_new_item);

        Button save = findViewById(R.id.save);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(DishList.this, EditDish.class);
                startActivityForResult(intent, 1000);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });


        final SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "edit"
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getApplicationContext());
                editItem.setBackground(new ColorDrawable(Color.rgb(0xff, 0xff,
                        0xff)));
                editItem.setWidth(170);
                editItem.setIcon(R.drawable.ic_edit);
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xff,
                        0xff, 0xff)));
                deleteItem.setWidth(170);
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);

            }
        };

// set creator
        listView.setMenuCreator(creator);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(DishList.this,Integer.toString(position+1)+ " Item Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {



                switch (index) {
                    case 0:
                     //edit
                        break;
                    case 1:
                        adapter.remove(list.get(position));
                        //dlist.remove(position);
                        adapter.notifyDataSetChanged();
                        //dadapter.notifyDataSetChanged();
                        //stringList(list);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000)
        {
            if(resultCode==Activity.RESULT_OK) {
                item = "             "+data.getStringExtra("Dish_Name") +"                    "+ data.getStringExtra("Temperature")+ "                    "+ data.getStringExtra("Time");
                //Dish item = new Dish(data.getStringExtra("Dish_Name"),data.getStringExtra("Temperature"),data.getStringExtra("Time"));
                Log.d(TAG, "onActivityResult: " + item);
                Dish ditem = new Dish();
                ditem.setDishName(ditem.getDishName());
                ditem.setTemp(ditem.getTemp());
                ditem.setTime(ditem.getTime());
                //dlist.add(ditem);
                //dadapter.notifyDataSetChanged();
                Log.d(TAG, "onActivityResult: DishName " + ditem.getDishName());
                Log.d(TAG, "onActivityResult: Temperature" + ditem.getTemp());
                Log.d(TAG, "onActivityResult: Time" + ditem.getTime());
                list.add(item);
                //stringList(list);
                //Alist.set(0,it);
                //Log.d(TAG, "onActivityResult:" + Alist.toString());
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);

        String dishType = MainActivity.dishPreference.getString(MainActivity.DISH_SELECTED, null);

        if(dishType.equals("vessel")){
            editor.putString("vessel list", json);
        }else if(dishType.equals("kadai")){
            editor.putString("kadai list", json);
        }else if(dishType.equals("pan")){
            editor.putString("pan list", json);
        }else{
            editor.putString("cooker list", json);
        }

        editor.apply();
    }

    private void loadData() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        String dishType = MainActivity.dishPreference.getString(MainActivity.DISH_SELECTED, null);
        String json = "";

        if(dishType.equals("vessel")){
            json = sharedPreferences.getString("vessel list", null);
        }else if(dishType.equals("kadai")){
            json = sharedPreferences.getString("kadai list", null);
        }else if(dishType.equals("pan")){
            json = sharedPreferences.getString("pan list", null);
        }else{
            json = sharedPreferences.getString("cooker list", null);
        }

        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        list = gson.fromJson(json, type);

        if (list == null) {
            list = new ArrayList<>();
        }
    }

    public void stringList(ArrayList list){
        simpleArray = new String[ list.size() ];
        list.toArray( simpleArray );
    }
}
