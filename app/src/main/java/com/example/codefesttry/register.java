package com.example.codefesttry;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register extends AppCompatActivity {

    EditText emailLogin, password, username;
    Button register;
    DatabaseHelperMe dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.userName);
        emailLogin = findViewById(R.id.emailEdTxt);
        password = findViewById(R.id.passwordEdTxt);
        register = findViewById(R.id.RegisterBtn);

        dbhelper = new DatabaseHelperMe(this);

        register.setOnClickListener(v -> {
            String UN, email, pass;
            UN = username.getText().toString().trim();
            email = emailLogin.getText().toString().trim();
            pass = password.getText().toString().trim();

            if (UN.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(register.this, "Please fill the fields.", Toast.LENGTH_SHORT).show();
                return;
            }

                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!email.matches(emailRegex)) {
                    Toast.makeText(register.this, "Invalid email! format: example@gmail.com", Toast.LENGTH_SHORT).show();
                    return;
            }


                boolean isSaved = dbhelper.UserEntries(UN, email, pass);

                if (isSaved){
                    Intent intent = new Intent(register.this, MainActivity.class);
                    startActivity(intent);
                }
        });
    }


}

