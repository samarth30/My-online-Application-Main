package com.example.dell.myonlineapplicationmain.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dell.myonlineapplicationmain.R;
import com.example.dell.myonlineapplicationmain.R;

public class NewPassword extends AppCompatActivity {

    Animation frombottom, fromtop;
    EditText password1,password2;
    TextView textView4;
    Button btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        password1  =(EditText)findViewById(R.id.password_newpassword);
        password2 = (EditText)findViewById(R.id.password1_newpassword);

        btnChangePassword = (Button)findViewById(R.id.ChangePassword_newpassword);

        btnChangePassword.startAnimation(frombottom);
        password1.startAnimation(fromtop);
        password2.startAnimation(fromtop);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPassword.this,LoginAct.class);
                Toast.makeText(NewPassword.this, "password changed succesfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
