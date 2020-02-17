package com.example.ddprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_edition);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, toolbar, R.string.open_actionBar, R.string.close_actionBar);
        this.drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DescriptionFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_description);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_description :

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DescriptionFragment()).commit();

                break;

            case R.id.nav_caracComp :

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CaracCompFragment()).commit();

                break;

            case R.id.nav_capaDons :

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CapaDonsFragment()).commit();

                break;

            case R.id.nav_equipSorts :

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EquipSortsFragment()).commit();

                break;

            case R.id.nav_personnalité :

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PersonnaliteFragment()).commit();

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
                    //final Races test = new Races("dragonborn");
                    final SpellList test = new SpellList();
                    final Spell test2 = test.getSpell("aid");



                    CharacterEditionActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView text = findViewById(R.id.test);

                            //text.setText(Integer.toString(test.getSpeed()));
                            text.setText(test2.toString());

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
