package com.example.dell.myonlineapplicationmain.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dell.myonlineapplicationmain.R;

public class MainActivity extends AppCompatActivity {


    ImageView bgone;
    Button btnget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this,HomeActivity.class));
        }else {
            bgone = (ImageView) findViewById(R.id.bgone);
            btnget = (Button) findViewById(R.id.btnget);

            bgone.animate().scaleX(2).scaleY(2).setDuration(5000).start();

            btnget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a = new Intent(MainActivity.this, SignUpAct.class);
                    startActivity(a);
                }
            });
        }
    }
}
