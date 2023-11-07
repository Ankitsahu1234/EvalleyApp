package android.example.evalleyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.example.evalleyapp.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.MenuItem;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        DrawerLayout drawer = binding.drawer;
        NavigationView navigationView = binding.navmenu;
        toggle=new ActionBarDrawerToggle(this,drawer,binding.toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.icon_home:
                        temp=new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,temp).commit();
                        break;

                    case R.id.icon_area:
                        Intent intent=new Intent(MainActivity.this,AreaActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.icon_about:
                        Intent i=new Intent(MainActivity.this, AboutUs.class);
                        startActivity(i);
                        break;

                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}


//case R.id.icon_about:
//        temp=new AboutFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,temp).commit();
//        break;
