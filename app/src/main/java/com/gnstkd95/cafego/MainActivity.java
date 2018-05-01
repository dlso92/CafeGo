package com.gnstkd95.cafego;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView nav_iv_userimg;
    TextView nav_tv_username;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    BottomNavigationView bottomNavigationView;

    ViewPager pager;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(myAdapter);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        nav_tv_username = findViewById(R.id.nav_tv_username);
        nav_iv_userimg = findViewById(R.id.nav_iv_userimg);

        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.nav);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);

        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerToggle.syncState();

//        Glide.with(this).load(R.drawable.cafegoimg).into(nav_iv_userimg);

    }//onCreate..////////////////////////////////////////////////

    //네비게이션뷰
    NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_menu_member:
                    Toast.makeText(MainActivity.this, "회원 정보", Toast.LENGTH_SHORT).show();
                    break;
            }
            drawerLayout.closeDrawer(navigationView,true);
            return false;
        }
    };

    //바텀네비게이션뷰
               BottomNavigationView.OnNavigationItemSelectedListener onBottomNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.action_CafeSearch:
                            Toast.makeText(MainActivity.this, "CafeSearch", Toast.LENGTH_SHORT).show();
                            pager.setCurrentItem(0,false);
                            return true;
                        case R.id.action_Community:
                            Toast.makeText(MainActivity.this, "Community", Toast.LENGTH_SHORT).show();
                            pager.setCurrentItem(1,true);
                            return true;
                        case R.id.action_Ranking:
                            Toast.makeText(MainActivity.this, "Ranking", Toast.LENGTH_SHORT).show();
                            pager.setCurrentItem(2,true);
                            return true;
                        case R.id.action_MyCafe:
                            Toast.makeText(MainActivity.this, "MyCafe", Toast.LENGTH_SHORT).show();
                            pager.setCurrentItem(3,true);
                            return true;
                    }

            return false;
        }
    };

    //setting 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.setting_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_setting:
                new AlertDialog.Builder(this).setPositiveButton("Ok",null).create().show();
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
            break;
        }

        return super.onOptionsItemSelected(item);
    }


}//MainActrivity..//////////////////////////////////////////////
