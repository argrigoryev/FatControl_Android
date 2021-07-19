package com.example.fatcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    private int sex;
    private int age;
    private int weight;
    private int height;

    DecimalFormat df = new DecimalFormat("#.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getPreferences();
        setWeight();
        setKkal();
        setKetle();
    }

    private void getPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        sex = sharedPreferences.getInt("sex",1);
        age = sharedPreferences.getInt("age",20);
        weight = sharedPreferences.getInt("weight", 60);
        height = sharedPreferences.getInt("height", 170);
    }

    private void setWeight() {
        TextView weightText = (TextView)findViewById(R.id.weightText);
        int normalMaleKetle = 22;
        int normalFemaleKetle = 21;
        // female
        if (sex == 2) {
            weightText.setText(String.valueOf(Math.round(normalFemaleKetle * Math.pow((float)height/100, 2))) + " кг");
        } else {
            weightText.setText(String.valueOf(Math.round(normalMaleKetle * Math.pow((float)height/100, 2))) + " кг");
        }
    }

    private void setKkal() {
        TextView kkalText = (TextView)findViewById(R.id.kkalText);
        // female
        if (sex == 2) {
            kkalText.setText(String.valueOf(Math.round(447.6 + (9.2 * (float)weight) + (3.1 * (float)height) - (4.3 * (float)age))) + " ккал");
        } else {
            kkalText.setText(String.valueOf(Math.round(88.36 + (13.4 * (float)weight) + (4.8 * (float)height) - (5.7 * (float)age))) + " ккал");
        }
    }

    private void setKetle() {
        TextView ketleText = (TextView)findViewById(R.id.ketleText);
        ketleText.setText(df.format((float)weight / (Math.pow((float)height/100, 2))));
    }

    public void onNormalWeightButtonClick(View view) {
        Intent normalWeightIntent = new Intent(ResultActivity.this, NormalWeightActivity.class);
        startActivity(normalWeightIntent);
    }

    public void onKetleButtonClick(View view) {
        Intent ketleIntent = new Intent(ResultActivity.this, KetleActivity.class);
        startActivity(ketleIntent);
    }

    public void onKkalButtonClick(View view) {
        Intent kkalIntent = new Intent(ResultActivity.this, KkalActivity.class);
        startActivity(kkalIntent);
    }
}