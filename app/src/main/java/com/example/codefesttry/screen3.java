package com.example.codefesttry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class screen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen3);

        TextView resultTxt = findViewById(R.id.resultTxt);
        SharedPreferences sharedPreferences = getSharedPreferences("KEY", MODE_PRIVATE);
        Long niga = sharedPreferences.getLong("RESULT", 0L);
        double finalRes = Double.longBitsToDouble(niga);

        resultTxt.setText(String.valueOf(finalRes));


    }
}