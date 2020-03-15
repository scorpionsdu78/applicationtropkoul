package com.example.ddprojet.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.example.ddprojet.fragment.DescriptionFragment;
import com.example.ddprojet.fragment.RaceClassSelectionFragment;
import com.example.ddprojet.fragment.RaceFragment;
import com.example.ddprojet.fragment.SpellsFragment;
import com.example.ddprojet.model.Character;
import com.example.ddprojet.model.Feature;
import com.example.ddprojet.model.ProficienciesList;
import com.example.ddprojet.model.Trait;
import com.example.ddprojet.model.TraitsList;
import com.example.ddprojet.persistance.FileJson;
import com.example.ddprojet.persistance.JsonParser;
import com.example.ddprojet.util.adapter.Adapter;
import com.example.ddprojet.util.CustomViewPager;
import com.example.ddprojet.util.FragmentEnum;
import com.example.ddprojet.util.Requirement;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class CharacterEditionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private CustomViewPager pager;

    public ImageView imageViewDescriptionFragmentAvatar;

    private Character character;

    private List<String> bonusSkill;
    private Map<String, Integer> classRequierement;
    private Map<String, Integer> bonusCharac;
    private String race;
    private String class_;

    private List<Trait> traits;
    private TraitsList optionalTraits;

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


    public int getClassRequierement(String key){
        if(this.classRequierement.containsKey(key))
            return this.classRequierement.get(key);

        return 0;
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

    public void setTraits(List<Trait> traits, TraitsList optionalTraits){

        this.traits = traits;
        this.optionalTraits = optionalTraits;

    }

    public List<Trait> getTraits(){
        return this.traits;
    }

    public TraitsList getOptionalTraits(){
        return this.optionalTraits;
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

    public boolean addSkill(String skill){
        return this.bonusSkill.add(skill);
    }

    public boolean removeSkill(String skill){
        return this.bonusSkill.remove(skill);
    }

    public List<Feature> getFeatureToChoose() {
        return featureToChoose;
    }

    public void setFeatureToChoose(List<Feature> featureToChoose) {
        this.featureToChoose = featureToChoose;
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


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawerLayout = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        this.navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawerLayout, toolbar, R.string.open_actionBar, R.string.close_actionBar);
        this.drawerLayout.addDrawerListener(toggle);

        // rend inaccessible les étapes non validés dans le drawer
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

    public void submit(View v){
        JsonParser parser = new JsonParser(this.character);
        parser.printJson();

        FileJson test = new FileJson(parser,v.getContext(),character.getName()+".json");
        test.save();



        if(character.isSaveble()){
            Intent callActivity = new Intent(getApplicationContext(), CharacterDisplayActivity.class);
            callActivity.putExtra("value", this.character);
            Toast.makeText(this, "Character saved !", Toast.LENGTH_LONG).show();
            startActivity(callActivity);
        }else{
            Toast.makeText(this, "Veuillez suivre les étapes de création", Toast.LENGTH_LONG).show();
        }


    }


    //Handle the change of the avatar from DescriptionFragment
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "Get result !", Toast.LENGTH_SHORT).show();
        System.out.println(requestCode);

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            Toast.makeText(this, "Failt!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (requestCode == 1) {
            Toast.makeText(this, "Gallery", Toast.LENGTH_SHORT).show();
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    saveImage(bitmap);
                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageViewDescriptionFragmentAvatar.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == 2) {
            Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageViewDescriptionFragmentAvatar.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    //Save the new image from the camera to the gallery
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + "/D&D Project");
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            this.character.setAvatarPath(f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


}

