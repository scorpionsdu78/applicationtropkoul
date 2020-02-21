package com.example.ddprojet;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.ddprojet.fragment.CapaDonsFragment;
import com.example.ddprojet.fragment.CaracCompFragment;
import com.example.ddprojet.fragment.ClassesFragment;
import com.example.ddprojet.fragment.EquipSortsFragment;
import util.FragmentEnum;
import com.example.ddprojet.fragment.PersonnaliteFragment;
import com.example.ddprojet.fragment.RaceFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Character;
import util.CustomViewPager;
import util.Adapter;
import util.Requirement;

public class CharacterEditionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private Adapter sectionAdapter;
    private CustomViewPager pager;

    private Character character;

    private List<String> bonuses;
    private Map<String, Integer> classRequierement;
    private Map<String, Integer> bonusCharac;


    public Character getCharacter() {
        return character;
    }

    public boolean containBonus(String key){
        return this.bonuses.contains(key);
    }


    public int getClassRequierement(String key){
        if(this.classRequierement.containsKey(key))
            return this.classRequierement.get(key);

        return 0;
    }


    public int levelBonusSkill(){
        return 2;
    }

    public int levelBonusCharac(){
        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_edition);

        this.character = new Character();

        this.bonuses = new ArrayList<>();
        this.classRequierement = new HashMap<>();
        bonusCharac = new HashMap<>();

        bonusCharac.put("STR", new Integer(2));
        bonusCharac.put("CHA", new Integer(1));


        this.bonuses.add("Acrobatics");
        this.bonuses.add("Sleight of Hand");
        this.bonuses.add("History");
        this.bonuses.add("Investigation");
        this.bonuses.add("Religion");
        this.bonuses.add("Animal Handling");
        this.bonuses.add("Medicine");
        this.bonuses.add("Survival");
        this.bonuses.add("Intimidation");
        this.bonuses.add("Performance");
        this.bonuses.add("Persuasion");



        sectionAdapter = new Adapter(getSupportFragmentManager());

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, toolbar, R.string.open_actionBar, R.string.close_actionBar);
        this.drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        pager = (CustomViewPager) findViewById(R.id.fragment_container);





        SetFragment(pager);




        /*if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new RaceFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_description);
        }*/

    }

    private void SetFragment(ViewPager pager){
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.add(new RaceFragment());
        adapter.add(new ClassesFragment());
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


    public void onBackButtonClick(View v){
        this.ChangeFragment(pager.getCurrentItem() - 1);
    }

    public void onNextButtonClick(View v){
        this.ChangeFragment(pager.getCurrentItem() + 1);
    }

    public void SetRequirement(String classe){
        switch (classe){
            case "Barbarian":
                classRequierement.put(Requirement.Barbare.getStat1(),13);
                break;

            case "Bard":
                classRequierement.put(Requirement.Barde.getStat1(),13);
                break;

            case "Cleric":
                classRequierement.put(Requirement.Clerc.getStat1(),13);
                break;

            case "Druid":
                classRequierement.put(Requirement.Druide.getStat1(),13);
                break;

            case "Fighter":
                classRequierement.put(Requirement.Guerrier.getStat1(),13);
                classRequierement.put(Requirement.Guerrier.getStat2(), 13);
                break;

            case "Monk":
                classRequierement.put(Requirement.Moine.getStat1(), 13);
                classRequierement.put(Requirement.Moine.getStat2(),13);
                break;

            case "Paladin":
                classRequierement.put(Requirement.Paladin.getStat1(), 13);
                classRequierement.put(Requirement.Paladin.getStat2(),13);
                break;

            case "Ranger":
                classRequierement.put(Requirement.Rodeur.getStat1(),13);
                classRequierement.put(Requirement.Rodeur.getStat2(),13);
                break;

            case "Rogue":
                classRequierement.put(Requirement.Roublard.getStat1(),13);
                break;

            case "Sorcerer":
                classRequierement.put(Requirement.Ensorceleur.getStat1(),13);
                break;

            case "Warlock":
                classRequierement.put(Requirement.Sorcier.getStat1(),13);
                break;

            case "Wizard":
                classRequierement.put(Requirement.Magicien.getStat1(), 13);
                break;
        }
    }




}

