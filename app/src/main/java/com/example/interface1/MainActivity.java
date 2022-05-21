package com.example.interface1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.interface1.databinding.ActivityMainBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
//    private Document doc;
//    private Thread secThread;
//    private Runnable runnable;
//    private ListView listView;
//    private CustomArrayAdapter adapter;
//    private List<ListItemClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_help, R.id.act)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        listView = findViewById(R.id.listView1);
//        arrayList = new ArrayList<>();
//        adapter = new CustomArrayAdapter(this, R.layout.listitem1, arrayList, getLayoutInflater());
//        listView.setAdapter(adapter);
//        init();


        Button but =findViewById(R.id.button);
        View.OnClickListener on =new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent serviceIntent = new Intent(MainActivity.this, foreService.class);
                        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
                        ContextCompat.startForegroundService(MainActivity.this, serviceIntent);

                    }

        };
        but.setOnClickListener(on);






    }
//    private void init() {
//
//
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                getWeb();
//            }
//        };
//        secThread = new Thread(runnable);
//        secThread.start();
//
//    }
//    private void getWeb() {
//        try {
//            doc = Jsoup.connect("https://www.banki.ru/products/currency/cash/kursk/").get();
//            Elements tables = doc.getElementsByTag("tbody");
//            Element table = tables.get(0);
//            Elements elements_from_table = table.children();
//            Log.d("MyLog", "size: " + table.children().get(1).child(2).text());
//
//            ListItemClass items = new ListItemClass();
//            items.setData_1(table.children().get(1).child(1).text());
//            items.setData_2(table.children().get(1).child(2).text());
//
//            arrayList.add(items);
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    adapter.notifyDataSetChanged();
//
//                }
//            });
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        int a = 0;
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}