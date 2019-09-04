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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.dell.myonlineapplicationmain.R;

import org.json.JSONException;
import org.json.JSONObject;

public class forget extends AppCompatActivity {

    Animation frombottom, fromtop;
    Button btnGenerateOtp;
    EditText otp_enter_forget, email_forget;
    TextView textview3;
    public int l = 1;
    String FORGET_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        btnGenerateOtp = (Button) findViewById(R.id.generate_otp_forget);

        email_forget = (EditText)findViewById(R.id.email_forget);
        otp_enter_forget = (EditText)findViewById(R.id.text_otp_forget);

        textview3 = (TextView)findViewById(R.id.textView3);

        btnGenerateOtp.startAnimation(frombottom);

        textview3.startAnimation(fromtop);
        email_forget.startAnimation(fromtop);
        otp_enter_forget.startAnimation(fromtop);

        btnGenerateOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (l == 1) {
                    btnGenerateOtp.setText("VERIFY");
                    l=0;
                } else {
                    Intent intent = new Intent(forget.this, NewPassword.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void forgetPassword(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, FORGET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

}
