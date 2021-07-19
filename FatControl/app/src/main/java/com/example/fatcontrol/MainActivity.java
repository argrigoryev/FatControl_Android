package com.example.fatcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    // adMob = ca-app-pub-5736477854640852~7836070911

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSexSelect();

        AdView adView = (AdView)findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }

    public void onCountButtonClick(View view) {
        if (isUserInfoCorrect()) {
            setUserInfo();
            Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(resultIntent);
        } else {
            Toast.makeText(this, "Пожалуйта, введите корректные данные", Toast.LENGTH_LONG).show();
        }
    }

    private void setUserInfo() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // sex
        Spinner spinner = (Spinner)findViewById(R.id.sexSelect);
        int sex = spinner.getSelectedItemPosition();
        editor.putInt("sex", sex);
        // age
        EditText ageInput = (EditText)findViewById(R.id.ageInput);
        int age = parseInt(ageInput.getText().toString());
        editor.putInt("age", age);
        // weight
        EditText weightInput = (EditText)findViewById(R.id.weightInput);
        int weight = parseInt(weightInput.getText().toString());
        editor.putInt("weight", weight);
        // height
        EditText heightInput = (EditText)findViewById(R.id.heightInput);
        int height = parseInt(heightInput.getText().toString());
        editor.putInt("height", height);
        // save
        editor.apply();
    }

    private boolean isUserInfoCorrect() {
        Spinner spinner = (Spinner)findViewById(R.id.sexSelect);
        boolean isSexSelected = spinner.getSelectedItemPosition() != 0;
        return isSexSelected && isAgeCorrect() && isWeightCorrect() && isHeightCorrect();
    }

    private boolean isAgeCorrect() {
        EditText ageInput = (EditText)findViewById(R.id.ageInput);
        boolean isAgeFilled = !ageInput.getText().toString().equals("");
        if (isAgeFilled) {
            int age = parseInt(ageInput.getText().toString());
            return age > 10 && age < 140;
        }
        return false;
    }

    private boolean isWeightCorrect() {
        EditText weightInput = (EditText)findViewById(R.id.weightInput);
        boolean isWeightFilled = !weightInput.getText().toString().equals("");
        if (isWeightFilled) {
            int weight = parseInt(weightInput.getText().toString());
            return weight > 40 && weight < 200;
        }
        return false;
    }

    private boolean isHeightCorrect() {
        EditText heightInput = (EditText)findViewById(R.id.heightInput);
        boolean isHeightFilled = !heightInput.getText().toString().equals("");
        if (isHeightFilled) {
            int height = parseInt(heightInput.getText().toString());
            return height > 100 && height < 250;
        }
        return false;
    }

    private void setSexSelect() {
        String[] items = new String[] {"Не выбрано", "Мужской", "Женский"};
        Spinner spinner = (Spinner)findViewById(R.id.sexSelect);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}