package com.example.codefesttry;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefesttry.databinding.ActivityHomePageBottomNavBinding;

public class HomePageBottomNav extends AppCompatActivity {

    private ActivityHomePageBottomNavBinding binding;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBottomNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home_page_bottom_nav);

        // ❌ DELETE OR COMMENT OUT THIS LINE:
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Keep this line! This is what actually connects the bottom buttons to the fragments.
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

}