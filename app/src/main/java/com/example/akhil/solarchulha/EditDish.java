package com.example.akhil.solarchulha;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditDish extends AppCompatActivity {
    private static final String TAG = "EditDish";

    EditText dishName;
    EditText temp;
    EditText time;
    String dishText;
    String tempText;
    String timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);

        Button save = findViewById(R.id.save);

        dishName = (EditText)findViewById(R.id.dish_name);

        temp = (EditText)findViewById(R.id.temp);

        time = (EditText)findViewById(R.id.time);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dishText = dishName.getText().toString();
                tempText = temp.getText().toString();
                timeText = time.getText().toString();
                Intent intent = new Intent();
                Log.d(TAG, "onClick: ");
                intent.putExtra("Dish_Name",dishText);
                intent.putExtra("Temperature",tempText);
                intent.putExtra("Time",timeText);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
