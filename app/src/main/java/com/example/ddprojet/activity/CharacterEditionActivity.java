package com.example.ddprojet.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.ddprojet.R;
import com.example.ddprojet.fragment.BonusesSelectionFragment;
import com.example.ddprojet.fragment.CharacSkillsFragment;
import com.example.ddprojet.fragment.ClassFragment;
import com.example.ddprojet.fragment.SpellsFragment;

import com.example.ddprojet.model.Feature;
import com.example.ddprojet.model.ProficienciesList;
import com.example.ddprojet.model.TraitList;
import com.example.ddprojet.util.FragmentEnum;
import com.example.ddprojet.fragment.DescriptionFragment;
import com.example.ddprojet.fragment.RaceClassSelectionFragment;
import com.example.ddprojet.fragment.RaceFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ddprojet.model.Character;
import com.example.ddprojet.util.CustomViewPager;
import com.example.ddprojet.util.Adapter;
import com.example.ddprojet.util.Requirement;

public class CharacterEditionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private CustomViewPager pager;

    private Character character;

    private List<String> bonusSkill;
    private Map<String, Integer> classRequierement;
    private Map<String, Integer> bonusCharac;
    private String race;
    private String classe;

    private TraitList traits;
    private TraitList optionalTrait;

    private List<Feature> featureToChoose;

    private List<ProficienciesList> listToChoseFrom;

    public List<ProficienciesList> getListToChoseFrom() {
        return listToChoseFrom;
    }

    public void setListToChoseFrom(List<ProficienciesList> listToChoseFrom) {
        this.listToChoseFrom = listToChoseFrom;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Character getCharacter() {
        return character;
    }

    public boolean containBonusSkill(String key){
        return this.bonusSkill.contains(key);
    }
    public int getBonusCharac(String key){

        if(this.bonusCharac.containsKey(key))
            return this.bonusCharac.get(key);
        else
            return 0;

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



        this.bonusSkill = new ArrayList<>();
        this.classRequierement = new HashMap<>();
        this.bonusCharac = new HashMap<>();



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        this.navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, toolbar, R.string.open_actionBar, R.string.close_actionBar);
        this.drawerLayout.addDrawerListener(toggle);

        SubMenu subMenu = this.navigationView.getMenu().getItem(0).getSubMenu();
        for(int i = 1; i < subMenu.size(); i++){
            subMenu.getItem(i).setCheckable(false);
        }

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
        adapter.add(new RaceClassSelectionFragment());
        adapter.add(new RaceFragment());
        adapter.add(new ClassFragment());
        adapter.add(new BonusesSelectionFragment());
        adapter.add(new CharacSkillsFragment());
        adapter.add(new SpellsFragment());
        adapter.add(new DescriptionFragment());
        pager.setAdapter(adapter);
    }

    public void ChangeFragment(FragmentEnum fragment){

        this.navigationView.getMenu().getItem(0).getSubMenu().getItem(fragment.getNavItemPos()).setCheckable(true);
        this.navigationView.setCheckedItem(fragment.getNavItemId());
        this.pager.setCurrentItem(fragment.getValue());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(!item.isCheckable())
            return false;


        switch (item.getItemId()){

            case R.id.nav_raceClass :

                ChangeFragment(FragmentEnum.RaceClassSelection);

                break;

            case R.id.nav_bonusesSelection :

                ChangeFragment(FragmentEnum.BonusesSelection);

                break;

            case R.id.nav_characSkills :

                ChangeFragment(FragmentEnum.CharacSkills);

                break;

            case R.id.nav_Spells :

                ChangeFragment(FragmentEnum.Spells);

                break;

            case R.id.nav_description:

                ChangeFragment(FragmentEnum.Description);

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
        this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);
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

    public void setBonusCharac(HashMap<String,Integer> _bonusCharac) {

            this.bonusCharac = _bonusCharac;

    }

    public void setTraits(TraitList l1, TraitList l2){

        this.traits = l1;
        this.optionalTrait =l2;

    }

    public void addSkill(String skill){
        this.bonusSkill.add(skill);
    }

    public List<Feature> getFeatureToChoose() {
        return featureToChoose;
    }

    public void setFeatureToChoose(List<Feature> featureToChoose) {
        this.featureToChoose = featureToChoose;
    }
}

