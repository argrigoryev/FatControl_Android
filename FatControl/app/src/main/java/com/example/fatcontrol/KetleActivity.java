package com.example.fatcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class KetleActivity extends AppCompatActivity {

    private int sex;
    private int age;
    private int weight;
    private int height;

    DecimalFormat df = new DecimalFormat("#.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketle);
        getPreferences();
        setKetle();
        setKetleRange();
    }

    private void getPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        sex = sharedPreferences.getInt("sex",1);
        age = sharedPreferences.getInt("age",20);
        weight = sharedPreferences.getInt("weight", 60);
        height = sharedPreferences.getInt("height", 170);
    }

    private void setKetle() {
        TextView ketleText = (TextView)findViewById(R.id.ketleText);
        ketleText.setText(df.format((float)weight / (Math.pow((float)height/100, 2))));
    }

    private void setKetleRange() {
        TextView ketleRangeText = (TextView)findViewById(R.id.ketleRangeText);
        ketleRangeText.setText(getMinValue() + " – " + getMaxValue());
    }

    private String getMinValue() {
        // female
        if (sex == 2) {
            return "19";
        }
        return "20";
    }

    private String getMaxValue() {
        // female
        if (sex == 2) {
            return "24";
        }
        return "25";
    }
}