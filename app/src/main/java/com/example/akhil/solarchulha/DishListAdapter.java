package com.example.akhil.solarchulha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akhil on 19-04-2018.
 */

public class DishListAdapter extends ArrayAdapter<Dish> {

    private static final String TAG = "DishListAdapter";
    private Context mContext;
    int mResource;

    public DishListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Dish> objects) {
        super(context, resource, objects);
        mContext = mContext;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String dishName = getItem(position).getDishName();
        String temp = getItem(position).getTemp();
        String time = getItem(position).getTime();

        Dish dish = new Dish();
        dish.setDishName(dishName);
        dish.setTemp(temp);
        dish.setTime(time);


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent,false);

        TextView tvDishName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvTemp = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvTime = (TextView) convertView.findViewById(R.id.textView3);
        //RadioButton rb = (RadioButton) convertView.findViewById(R.id.radioButton);

        tvDishName.setText(dishName);
        tvTemp.setText(temp);
        tvTime.setText(time);

        return convertView;
    }
}
