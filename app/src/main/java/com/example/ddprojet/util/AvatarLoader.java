package com.example.ddprojet.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.ddprojet.R;

import java.io.File;

public class AvatarLoader {

    private String avatarPath;

    public AvatarLoader(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Bitmap load(Resources res){

        if(this.avatarPath == null)
            return BitmapFactory.decodeResource(res, R.drawable.avatar_default);


        File imgFile = new File(this.avatarPath);

        if(imgFile.exists()){

            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }


        return BitmapFactory.decodeResource(res, R.drawable.avatar_default);
    }
}
