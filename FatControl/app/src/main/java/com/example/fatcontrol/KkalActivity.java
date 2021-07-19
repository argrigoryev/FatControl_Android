package com.example.fatcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class KkalActivity extends AppCompatActivity {

    private int sex;
    private int age;
    private int weight;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kkal);
        getPreferences();
        setGoalSelect();
    }

    private void getPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        sex = sharedPreferences.getInt("sex",1);
        age = sharedPreferences.getInt("age",20);
        weight = sharedPreferences.getInt("weight", 60);
        height = sharedPreferences.getInt("height", 170);
    }

    public void onMoreKkalButtonClick(View view) {
        Intent moreKkalIntent = new Intent(KkalActivity.this, KkalMoreActivity.class);
        startActivity(moreKkalIntent);
    }

    public void onProteinButtonClick(View view) {
        Intent proteinIntent = new Intent(KkalActivity.this, ProteinActivity.class);
        startActivity(proteinIntent);
    }

    public void onFatsButtonClick(View view) {
        Intent fatsIntent = new Intent(KkalActivity.this, FatsActivity.class);
        startActivity(fatsIntent);
    }

    public void onCarbonButtonClick(View view) {
        Intent carbonIntent = new Intent(KkalActivity.this, CarbonActivity.class);
        startActivity(carbonIntent);
    }

    private void setGoalSelect() {
        String[] items = new String[] {"Поддержание веса", "Похудение", "Набор массы"};
        Spinner spinner = (Spinner)findViewById(R.id.goalSelect);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setKkal(position);
                setProtein(position);
                setFats(position);
                setCarbon(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    private void setKkal(int goal) {
        TextView kkalText = (TextView)findViewById(R.id.kkalText);
        int kkal;
        if (goal == 1) { // losing
            kkal =  (int)Math.round(getKkal() * 0.85);
        } else if (goal == 2) { // gain
            kkal = (int)Math.round(getKkal() * 1.15);
        } else { // maintenance
            kkal = getKkal();
        }
        kkalText.setText(String.valueOf(kkal) + " ккал");
    }

    private void setProtein(int goal) {
        TextView proteinText = (TextView)findViewById(R.id.proteinText);
        int protein = (int)Math.round((getKkal() * 0.3) / 4);
        proteinText.setText(String.valueOf(protein) + " г");
    }

    private void setFats(int goal) {
        TextView fatsText = (TextView)findViewById(R.id.fatsText);
        int fats;
        if (goal == 1) { // losing
            fats = (int)Math.round((getKkal() * 0.3) / 9);
        } else if (goal == 2) { // gain
            fats = (int)Math.round((getKkal() * 0.15) / 9);
        } else { // maintenance
            fats = (int)Math.round((getKkal() * 0.2) / 9);
        }
        fatsText.setText(String.valueOf(fats) + " г");
    }

    private void setCarbon(int goal) {
        TextView carbonText = (TextView)findViewById(R.id.carbonText);
        int carbon;
        if (goal == 1) { // losing
            carbon = (int)Math.round((getKkal() * 0.4) / 9);
        } else if (goal == 2) { // gain
            carbon = (int)Math.round((getKkal() * 0.55) / 9);
        } else { // maintenance
            carbon = (int)Math.round((getKkal() * 0.5) / 9);
        }
        carbonText.setText(String.valueOf(carbon) + " г");
    }

    private int getKkal() {
        // female
        if (sex == 2) {
            return (int)Math.round(447.6 + (9.2 * (float)weight) + (3.1 * (float)height) - (4.3 * (float)age));
        } else {
            return (int)Math.round(88.36 + (13.4 * (float)weight) + (4.8 * (float)height) - (5.7 * (float)age));
        }
    }
}