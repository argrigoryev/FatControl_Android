package com.example.fatcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class NormalWeightActivity extends AppCompatActivity {

    private int sex;
    private int age;
    private int weight;
    private int height;

    DecimalFormat df = new DecimalFormat("#.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_weight);
        getPreferences();
        setWeightRange();
        setResult();
    }

    private void getPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        sex = sharedPreferences.getInt("sex",1);
        age = sharedPreferences.getInt("age",20);
        weight = sharedPreferences.getInt("weight", 60);
        height = sharedPreferences.getInt("height", 170);
    }

    private void setWeightRange() {
        TextView weightRangeText = (TextView)findViewById(R.id.weightRangeText);
        weightRangeText.setText(getMinValue() + " – " + getMaxValue());
    }

    private String getMinValue() {
        // female
        if (sex == 2) {
            return df.format(19 * Math.pow((float)height/100, 2));
        } else {
            return df.format(20 * Math.pow((float)height/100, 2));
        }
    }

    private String getMaxValue() {
        // female
        if (sex == 2) {
            return df.format(24 * Math.pow((float)height/100, 2));
        } else {
            return df.format(25 * Math.pow((float)height/100, 2));
        }
    }

    private void setResult() {
        TextView resultText = (TextView)findViewById(R.id.resultText);
        resultText.setText("У вас " + getResult());
    }

    private String getResult() {
        float ketle = (float) ((float)weight / (Math.pow((float)height/100, 2)));
        if (ketle <= 18.5) {
            return "дифицит массы тела";
        } else if (ketle > 18.5 && ketle <= 25) {
            return "нормальная масса тела";
        } else if (ketle > 25 && ketle <= 30) {
            return "избыточная масса тела (предожирение)";
        } else if (ketle > 30 && ketle <= 35) {
            return "ожирение I степени";
        } else if (ketle > 35 && ketle < 40) {
            return "ожирение II степени";
        } else {
            return "ожирение III степени";
        }
    }
}