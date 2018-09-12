package com.example.qamberhaider.mazdor_user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new Jobs_Fragment();
                            break;
                        case R.id.navigation_dashboard:
                            selectedFragment = new Mazdor_Fragment();
                            break;
                        case R.id.navigation_notifications:
                            selectedFragment = new Feedback_Fragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fram1,selectedFragment).commit();
                    return true;
                }
            };

}
