package com.example.ddprojet.CustomComponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ddprojet.R;

public class CharacterSelectionView extends LinearLayout {

    private TextView textView_name;
    private TextView textView_race;
    private TextView textView_class;
    private TextView textView_alignement;
    private TextView textView_level;

    private ImageView imageView_avatar;


    public CharacterSelectionView(@NonNull Context context){
        super(context, null);
    }

    public CharacterSelectionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.character_selection_view, this, true);

        this.textView_name = findViewById(R.id.textViewName);
        this.textView_race = findViewById(R.id.textViewClass);
        this.textView_class = findViewById(R.id.textViewClass);
        this.textView_alignement = findViewById(R.id.textViewAlignement);
        this.textView_level = findViewById(R.id.textViewLevel);

        this.imageView_avatar = findViewById(R.id.imageViewAvatar);

        setAttrs(context, attrs);
    }

    public CharacterSelectionView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.character_selection_view, this, true);

        this.textView_name = findViewById(R.id.textViewName);
        this.textView_race = findViewById(R.id.textViewClass);
        this.textView_class = findViewById(R.id.textViewClass);
        this.textView_alignement = findViewById(R.id.textViewAlignement);
        this.textView_level = findViewById(R.id.textViewLevel);

        this.imageView_avatar = findViewById(R.id.imageViewAvatar);

        setAttrs(context, attrs);
    }


    private void setAttrs(@NonNull Context context, @Nullable AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CharacterSelectionCard);

        int count = typedArray.getIndexCount();
        try{

            for (int i = 0; i < count; ++i) {

                int attr = typedArray.getIndex(i);
                // the attr corresponds to the title attribute
                if(attr == R.styleable.CharacterSelectionCard_name) {
                    this.textView_name.setText(typedArray.getString(attr));
                } else if(attr == R.styleable.CharacterSelectionCard_race) {
                    this.textView_race.setText(typedArray.getString(attr));
                } else if(attr == R.styleable.CharacterSelectionCard_Class) {
                    this.textView_class.setText(typedArray.getString(attr));
                } else if(attr == R.styleable.CharacterSelectionCard_alignement) {
                    this.textView_alignement.setText(typedArray.getString(attr));
                } else if(attr == R.styleable.CharacterSelectionCard_level) {
                    this.textView_level.setText(typedArray.getString(attr));
                } else if(attr == R.styleable.CharacterSelectionCard_avatar) {
                    this.imageView_avatar.setImageResource(typedArray.getResourceId(attr, R.drawable.avatar_dark_wizard));
                }
            }
        }

        // the recycle() will be executed obligatorily
        finally {
            // for reuse
            typedArray.recycle();
        }
    }


    public void setName(String name) {
        this.textView_name.setText(name);
    }

    public void setRace(String race) {
        this.textView_race.setText(race);
    }

    public void setClass(String class_) {
        this.textView_class.setText(class_);
    }

    public void setAlignement(String alignement) {
        this.textView_alignement.setText(alignement);
    }

    public void setLevel(String level) {
        this.textView_level.setText(level);
    }

    public void setAvatar(@DrawableRes int avatar) {
        this.imageView_avatar.setImageResource(avatar);
    }
}
