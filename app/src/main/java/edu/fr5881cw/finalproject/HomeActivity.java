package edu.fr5881cw.finalproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_camera:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, new ContractListFragment(), ContractListFragment.FRAGMENT_TAG)
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_gallery:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, new GalleryFragment(), GalleryFragment.FRAGMENT_TAG)
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_map:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, new MapFragment(), MapFragment.FRAGMENT_TAG)
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ContractListFragment(), ContractListFragment.FRAGMENT_TAG).commit();
    }
}
