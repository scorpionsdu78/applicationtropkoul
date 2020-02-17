package com.example.ddprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.ddprojet.fragment.CapaDonsFragment;
import com.example.ddprojet.fragment.CaracCompFragment;
import com.example.ddprojet.fragment.Classes;
import com.example.ddprojet.fragment.EquipSortsFragment;
import com.example.ddprojet.fragment.FragmentEnum;
import com.example.ddprojet.fragment.PersonnaliteFragment;
import com.example.ddprojet.fragment.RaceFragment;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;

import java.io.IOException;

import connection.SpellList;
import model.Spell;


/*import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import connection.APIconnection;
import connection.Classes;*/

public class CharacterEditionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private Adapter sectionAdapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_edition);

        sectionAdapter = new Adapter(getSupportFragmentManager());

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, toolbar, R.string.open_actionBar, R.string.close_actionBar);
        this.drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        pager = (ViewPager) findViewById(R.id.fragment_container);

        SetFragment(pager);

        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


        /*if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new RaceFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_description);
        }*/

    }

    private void SetFragment(ViewPager pager){
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.add(new RaceFragment());
        adapter.add(new Classes());
        adapter.add(new CaracCompFragment());
        adapter.add(new CapaDonsFragment());
        adapter.add(new EquipSortsFragment());
        adapter.add(new PersonnaliteFragment());
        pager.setAdapter(adapter);
    }

    public void ChangeFragment(int id){
        pager.setCurrentItem(id);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_description :

                ChangeFragment(FragmentEnum.Race.getValue());

                break;

            case R.id.nav_caracComp :

                ChangeFragment(FragmentEnum.CaracComp.getValue());

                break;

            case R.id.nav_capaDons :

                ChangeFragment(FragmentEnum.CapaDons.getValue());

                break;

            case R.id.nav_equipSorts :

                ChangeFragment(FragmentEnum.Equip.getValue());

                break;

            case R.id.nav_personnalité :

                ChangeFragment(FragmentEnum.Personnaliter.getValue());

                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {

        if(this.drawerLayout.isDrawerOpen(GravityCompat.START))
            this.drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();

    }


    public void test(View V){
        new Thread(new Runnable(){

            public void run(){
                try {
                    //final String test = new String("dragonborn");
                    final SpellList test = new SpellList();
                    final Spell test2 = test.getSpell("aid");



                    CharacterEditionActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //TextView text = findViewById(R.id.test);

                            //text.setText(Integer.toString(test.getSpeed()));
                            //text.setText(test2.toString());

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
