package com.example.dell.myonlineapplicationmain.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.myonlineapplicationmain.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpAct extends AppCompatActivity {

    Animation frombottom, fromtop;
    Button btnjoin_signup,btnLogin_signup;
    TextView textView2;
    EditText email_signup, password_signup,username_signup ;
    ProgressBar loading;

    public String URL = "https://satyapaulmlk6.pythonanywhere.com/user/signup/";
    public static final String KEY_USERNAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //volley request quee

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        loading = (ProgressBar)findViewById(R.id.loading_signup);
        btnjoin_signup = (Button) findViewById(R.id.btnjoin_signup);
        btnLogin_signup = (Button)findViewById(R.id.btnLogin_signup);

        textView2 = (TextView) findViewById(R.id.textView2);

        email_signup = (EditText) findViewById(R.id.Email_Sign_Up);
        username_signup = (EditText) findViewById(R.id.user_name_signup);
        password_signup = (EditText) findViewById(R.id.password_signup);

        btnjoin_signup.startAnimation(frombottom);
        btnLogin_signup.startAnimation(frombottom);

        textView2.startAnimation(fromtop);

        email_signup.startAnimation(fromtop);
        username_signup.startAnimation(fromtop);
        password_signup.startAnimation(fromtop);

        btnLogin_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpAct.this,LoginAct.class);
                startActivity(intent);
            }
        });

        btnjoin_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             CreateAccount();
            }
        });

    }

    private void CreateAccount() {
        String name = username_signup.getText().toString().trim();
        String email = email_signup.getText().toString().trim();
        String password = password_signup.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please Enter the name...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter some Phone number ...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter the password", Toast.LENGTH_SHORT).show();
        }
        else{
            loading.setVisibility(View.VISIBLE);
            btnjoin_signup.setVisibility(View.GONE);
            register(name,email,password);
        }
    }

    private void register(final String name,final String email,final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(SignUpAct.this, "signing in process", Toast.LENGTH_SHORT).show();
                    Boolean UserCreated = jsonObject.getBoolean("User Created");
                    Toast.makeText(SignUpAct.this, "signing in", Toast.LENGTH_SHORT).show();

                    if(UserCreated) {
                        Intent intent = new Intent(SignUpAct.this,LoginAct.class);
                        startActivity(intent);
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpAct.this, e.toString(), Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    btnjoin_signup.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpAct.this, error.toString(), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                btnjoin_signup.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
              Map<String,String> params = new HashMap<>();
              params.put(KEY_USERNAME,name);
              params.put(KEY_EMAIL,email);
              params.put(KEY_PASSWORD,password);
              return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
