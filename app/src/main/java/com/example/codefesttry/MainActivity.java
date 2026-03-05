package com.example.codefesttry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 1. REMOVED the 'username' EditText from the login screen variables
    EditText emailLogin, password;
    Button loginBtn;
    TextView register;
    DatabaseHelperMe dbhelper;
    public static final String UN = "ha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 2. ONLY looking for Email and Password in the login layout
        emailLogin = findViewById(R.id.emailEdTxt);
        password = findViewById(R.id.passwordEdTxt);
        loginBtn = findViewById(R.id.loginBtn);
        register = findViewById(R.id.RegisterBtn);
        dbhelper = new DatabaseHelperMe(this);

        register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, register.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(v ->  {
            // 3. ONLY grabbing the text from Email and Password
            String email = emailLogin.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty()){
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!email.matches(emailPattern)){
                Toast.makeText(MainActivity.this, "Invalid email format!", Toast.LENGTH_SHORT).show();
                return;
            }

            // 4. Checking the database using ONLY the 2 required arguments
            boolean isValidUser = dbhelper.checkUser(email, pass);

            if (isValidUser){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomePageBottomNav.class);

                // Note: Right now, we are passing the hardcoded string "ha" as the username.
                // We will need to grab their actual username from the database!
                intent.putExtra(UN, "TestUser");

                startActivity(intent);
            } else {
                Toast.makeText(this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}