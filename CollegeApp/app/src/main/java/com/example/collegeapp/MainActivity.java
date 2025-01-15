package com.example.collegeapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.collegeapp.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private NavController nav_host_fragment;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private AppBarConfiguration appBarConfiguration;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;
    private final String CHECKEDITEM = "checked_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        switch (getCheckedItem()) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }

        try {
            // Initialize views
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            nav_host_fragment = Navigation.findNavController(this, R.id.nav_host_fragment);
            drawerLayout = findViewById(R.id.drawerLayout);
            navigationView = findViewById(R.id.navigation_view);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Initialize the ActionBarDrawerToggle
            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.start, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

            // Set up AppBarConfiguration
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_notice, R.id.navigation_faculty, R.id.navigation_gallery, R.id.navigation_about)
                    .setOpenableLayout(drawerLayout)
                    .build();

            // Set up navigation components
            NavigationUI.setupWithNavController(bottomNavigationView, nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, nav_host_fragment, appBarConfiguration);
            navigationView.setNavigationItemSelectedListener(this);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing app: ", e);
            Toast.makeText(this, "Error initializing app", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle != null && toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_developer) {
            startActivity(new Intent(this, DeveloperActivity.class));
        } else if (id == R.id.navigation_rate) {
            Toast.makeText(this, "Rate us", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.navigation_ebook) {
            startActivity(new Intent(this, EbookActivity.class));
        } else if (id == R.id.navigation_color) {
            showDialog();
        } else if (id == R.id.navigation_website) {
            // Open the website URL
            String url = "https://www.technoindiauniversity.ac.in";  // Replace with your custom website URL
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } else if (id == R.id.navigation_share) {
            shareImage();
        } else {
            Log.w(TAG, "Unhandled navigation item ID: " + id);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void shareImage() {
        Uri imageUri = getUriFromDrawable(R.drawable.image1);

        if (imageUri != null) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/jpeg");
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(shareIntent, "Share Image"));
        } else {
            Toast.makeText(this, "Error sharing image", Toast.LENGTH_SHORT).show();
        }
    }

    private Uri getUriFromDrawable(int drawableId) {
        Uri uri = null;
        try {
            File file = new File(getCacheDir(), "images");
            if (!file.exists()) {
                file.mkdirs();
            }
            File imageFile = new File(file, "shared_image.jpg");
            InputStream inputStream = getResources().openRawResource(drawableId);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
            uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", imageFile);
        } catch (IOException e) {
            Log.e(TAG, "Error creating URI for drawable", e);
        }
        return uri;
    }

    private void showDialog() {
        String[] themes = this.getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Selected Theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected = themes[i];
                checkedItem = i;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (selected == null) {
                    selected = themes[i];
                    checkedItem = i;
                }
                switch (selected) {
                    case "System Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                setCheckedItem(checkedItem);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int getCheckedItem() {
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }

    private void setCheckedItem(int i) {
        editor.putInt(CHECKEDITEM, i);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(nav_host_fragment, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
