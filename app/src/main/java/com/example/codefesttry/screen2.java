package com.example.codefesttry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class screen2 extends AppCompatActivity {

    TextView display, resultszz;
    EditText numb1, numb2;
    Spinner operators;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen2);

        btnNext = findViewById(R.id.btn_next);
        Intent intent = getIntent();
        String Name = intent.getStringExtra(MainActivity.UN);
        display = findViewById(R.id.display);
        display.setText("welcome home master " + Name);

        numb1 = findViewById(R.id.num1);
        numb2 = findViewById(R.id.num2);


        /*int number1 = Integer.parseInt(numb1.getText().toString());
        int number2 = Integer.parseInt(numb2.getText().toString());*/


        operators = findViewById(R.id.operators);
        /*resultszz = findViewById(R.id.resultz);*/


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = 0;

                double number1 = Double.parseDouble(numb1.getText().toString());
                double number2 = Double.parseDouble(numb2.getText().toString());
                switch (operators.getSelectedItem().toString()){
                    case "+":
                        result= number1 + number2;
                        break;
                    case "-":
                        result= number1 - number2;
                        break;
                    case "*":
                        result= number1 * number2;
                        break;
                    case "/":
                        if (number2 != 0) {
                            result = number1 / number2;
                        }
                        break;
                }
                /*resultszz.setText(String.valueOf(result));*/


                SharedPreferences sharedPreferences = getSharedPreferences("KEY", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                editor.putLong("RESULT", Double.doubleToRawLongBits(result));
                editor.apply();

                Intent intent = new Intent(screen2.this, screen3.class);
                startActivity(intent);
            }
        });
    }
}