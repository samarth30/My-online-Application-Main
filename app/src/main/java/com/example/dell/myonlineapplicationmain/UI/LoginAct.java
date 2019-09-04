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

import org.json.JSONException;
import org.json.JSONObject;
import com.example.dell.myonlineapplicationmain.R;

import java.util.HashMap;
import java.util.Map;

public class LoginAct extends AppCompatActivity {

    Animation frombottom, fromtop;
    Button btnjoin_login,btnLogin_login;
    TextView textView1,ForgetPassword;
    EditText password_login, email_login;
    ProgressBar loading;


    String URL="https://satyapaulmlk6.pythonanywhere.com/user/login/";

    String logout = "https://satyapaulmlk6.pythonanywhere.com/user/logout/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       if(SharedPrefManager.getInstance(this).isLoggedIn()){
           finish();
           startActivity(new Intent(LoginAct.this,HomeActivity.class));
       }
//        if(restorefData()){
//            Intent intent = new Intent(LoginAct.this,MainPage.class);
//            startActivity(intent);
//        }

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        loading = (ProgressBar)findViewById(R.id.loading_login);
        btnjoin_login = (Button) findViewById(R.id.btnjoin_login);
        btnLogin_login = (Button)findViewById(R.id.btnlogin_login);

        textView1 = (TextView) findViewById(R.id.textView1);
        ForgetPassword = (TextView)findViewById(R.id.forget_password_login);

        btnjoin_login.startAnimation(frombottom);
        btnLogin_login.startAnimation(frombottom);
        ForgetPassword.startAnimation(frombottom);

        email_login = (EditText) findViewById(R.id.email_login);
        password_login = (EditText) findViewById(R.id.password_login);

        email_login.startAnimation(fromtop);
        password_login.startAnimation(fromtop);

        btnjoin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAct.this,SignUpAct.class);
                startActivity(intent);

            }
        });

        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAct.this,forget.class);
                startActivity(intent);
            }
        });

        btnLogin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
    String email = email_login.getText().toString().trim();
    String password = password_login.getText().toString().trim();

    if(TextUtils.isEmpty(email)){
        Toast.makeText(this, "Please Enter your email", Toast.LENGTH_SHORT).show();
    }else if(TextUtils.isEmpty(password)){
        Toast.makeText(this, "please enter the password", Toast.LENGTH_SHORT).show();
    }else{
        loading.setVisibility(View.VISIBLE);
        btnLogin_login.setVisibility(View.GONE);
        AllowAcessToAccount(email,password);
        tokenSaved();
    }

    }

    private void tokenSaved() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, logout, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject("response");
                    Boolean LoggedOut = jsonObject.getBoolean("Loggesd Out");

                    if(LoggedOut){

                    }

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

    private void AllowAcessToAccount(final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Boolean Logged = jsonObject.getBoolean("Logged In");

//                    Toast.makeText(LoginAct.this, Logged, Toast.LENGTH_SHORT).show();
                    if(Logged) {
                        String token = jsonObject.getString("token");
                        Toast.makeText(LoginAct.this, token, Toast.LENGTH_SHORT).show();
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin("");
                        finish();
                        loading.setVisibility(View.GONE);
                        btnLogin_login.setVisibility(View.VISIBLE);
                        startActivity(new Intent(LoginAct.this,HomeActivity.class));
                    }



                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(LoginAct.this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    btnLogin_login.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginAct.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                btnLogin_login.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String, String> getParams(){
             Map<String,String> params = new HashMap<>();
             params.put("email",email);
             params.put("password",password);
             return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

//    public boolean restorefData() {
//
//        SharedPreferences Pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
//        Boolean isIntroActivityOpened = Pref.getBoolean("isIntroOpened",false);
//        return isIntroActivityOpened;
//
//    }
//
//    public void savepreference() {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putBoolean("isIntroOpened",true);
//        editor.commit();
//    }

}
