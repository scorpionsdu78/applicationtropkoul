package com.example.ddprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import connection.APIconnection;
import connection.Classes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View V){
        new Thread(new Runnable(){

            public void run(){
                try {
                    final Classes test = new Classes("ranger");
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView text = findViewById(R.id.test);
                            text.setText(test.getChoice().get(0).toString());

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
