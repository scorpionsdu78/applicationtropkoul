package com.example.ddprojet.persistance;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileJson {

    JsonParser parsed;
    Context context;
    String filename;

    public FileJson(JsonParser parsed, Context context, String filename) {
        this.parsed = parsed;
        this.context = context;
        this.filename = filename;
    }

    public boolean save(){

        try {
            FileOutputStream fos = context.openFileOutput(filename,context.MODE_PRIVATE);
            if(parsed != null){
                fos.write(parsed.getJson().getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete() throws IOException {
        String path = context.getFilesDir().getAbsolutePath() + "/" + this.filename;
        File file = new File(path);
        file.delete();
        if(file.exists()){
            file.getCanonicalFile().delete();
            if(file.exists()){
                context.deleteFile(file.getName());
                if(file.exists()){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isFilePresent() {
        String path = context.getFilesDir().getAbsolutePath() + "/" + this.filename;
        Log.i("testParse",path);
        File file = new File(path);
        return file.exists();
    }

    public String read(){
        try  {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }

            return sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}