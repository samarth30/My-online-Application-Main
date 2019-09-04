package com.example.dell.myonlineapplicationmain.UI;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.dell.myonlineapplicationmain.R;
import com.example.dell.myonlineapplicationmain.adapters.ExampleAdapter;
import com.example.dell.myonlineapplicationmain.adapters.MovieAdapter;
import com.example.dell.myonlineapplicationmain.adapters.MovieItemClickListener;
import com.example.dell.myonlineapplicationmain.adapters.ProductAdapterVertical;
import com.example.dell.myonlineapplicationmain.adapters.RecyclerItemClickListener;
import com.example.dell.myonlineapplicationmain.adapters.SliderPagerAdapter;
import com.example.dell.myonlineapplicationmain.models.ExampleItem;
import com.example.dell.myonlineapplicationmain.models.Movie;
import com.example.dell.myonlineapplicationmain.models.Product;
import com.example.dell.myonlineapplicationmain.models.Slide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.dell.myonlineapplicationmain.R.string.action_settings;
import static com.example.dell.myonlineapplicationmain.R.string.navigation_drawer_open;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Slide> lstSlides;
    private List<Product> lstMovies;
    private ViewPager slidePager;
    private TabLayout indicator;
    private RecyclerView MoviesRV,MoviesLiked,MoviesFav;
    RequestQueue mRequestQuee;
    RelativeLayout searchview;

    private ArrayList<ExampleItem> mExampleList;
    String URL ="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigation();
        mRequestQuee = Volley.newRequestQueue(this);
        mExampleList = new ArrayList<>();
        parseJson();

        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            searchview = findViewById(R.id.relLayout1);
            searchview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                    startActivity(intent);
                    Animatoo.animateZoom(HomeActivity.this);
                }
            });



            MoviesRV = findViewById(R.id.userList);
            MoviesLiked = findViewById(R.id.userList1);
            MoviesFav = findViewById(R.id.userList3);

            lstSlides = new ArrayList<>();
            ListSlidesnames();


            // dots indicator


            // timer for dots indicator


            //recycler View set up
            //init data

            lstMovies = new ArrayList<>();



            ListRecycleMovies();

            ExampleAdapter movieAdapter = new ExampleAdapter(this,mExampleList);
//            MovieAdapter movieAdapter = new MovieAdapter(this,lstMovies);
            ProductAdapterVertical productAdapterVertical = new ProductAdapterVertical(this, lstMovies);
            MoviesRV.setAdapter(movieAdapter);
            MoviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

            // liked movies

            MoviesLiked.setAdapter(productAdapterVertical);
            MoviesLiked.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));

            MoviesFav.setAdapter(productAdapterVertical);
            MoviesFav.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));

            MoviesRV.addOnItemTouchListener(
                    new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Intent intent = new Intent(HomeActivity.this, MovieDetailActivity.class);
                            Product currentItem = lstMovies.get(position);
                            intent.putExtra("title", currentItem.getTitle());
                            intent.putExtra("imgURL", currentItem.getImage());
                            intent.putExtra("imgCover", currentItem.getImage());
                            intent.putExtra("rating", String.valueOf(currentItem.getRating()));
                            intent.putExtra("price", String.valueOf(currentItem.getRating()));
                            intent.putExtra("short_desc", currentItem.getShortdesc());

                            startActivity(intent);
                        }
                    })
            );
            MoviesFav.addOnItemTouchListener(
                    new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Intent intent = new Intent(HomeActivity.this, MovieDetailActivity.class);
                            Product currentItem = lstMovies.get(position);
                            intent.putExtra("title", currentItem.getTitle());
                            intent.putExtra("imgURL", currentItem.getImage());
                            intent.putExtra("imgCover", currentItem.getImage());
                            intent.putExtra("rating", String.valueOf(currentItem.getRating()));
                            intent.putExtra("price", String.valueOf(currentItem.getRating()));
                            intent.putExtra("short_desc", currentItem.getShortdesc());

                            startActivity(intent);
                        }
                    })
            );
            MoviesLiked.addOnItemTouchListener(
                    new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Intent intent = new Intent(HomeActivity.this, MovieDetailActivity.class);
                            Product currentItem = lstMovies.get(position);
                            intent.putExtra("title", currentItem.getTitle());
                            intent.putExtra("imgURL", currentItem.getImage());
                            intent.putExtra("imgCover", currentItem.getImage());
                            intent.putExtra("rating", String.valueOf(currentItem.getRating()));
                            intent.putExtra("price", String.valueOf(currentItem.getRating()));
                            intent.putExtra("short_desc", currentItem.getShortdesc());
                            startActivity(intent);
                        }
                    })
            );
        }else{
            startActivity(new Intent(this,LoginAct.class));
        }
    }

    private void parseJson() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("user");
                        String shortDesc = jsonObject.getString("user");
                        String imageUrl = jsonObject.getString("webformatURL");
                        int rating = jsonObject.getInt("likes");
                        int price = jsonObject.getInt("likes");
                        Toast.makeText(HomeActivity.this, "added "+title, Toast.LENGTH_SHORT).show();
                        mExampleList.add(new ExampleItem(imageUrl,title,rating,price,shortDesc));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQuee.add(request);

    }





    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(slidePager.getCurrentItem() < lstSlides.size()-1){
                        slidePager.setCurrentItem(slidePager.getCurrentItem() +1);
                    }
                    else{
                        slidePager.setCurrentItem(0);
                    }
                }
            });
        }
    }
    private void ListRecycleMovies() {
        lstMovies.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        lstMovies.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        lstMovies.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        lstMovies.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        lstMovies.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        lstMovies.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        lstMovies.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
    }

    private void ListSlidesnames() {
        lstSlides.add(new Slide(R.drawable.a,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.b,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.c,"Slide Title \nmore text here"));
        lstSlides.add(new Slide(R.drawable.d,"Slide Title \nmore text here"));
    }

    private void navigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setBackgroundColor(getResources().getColor(R.color.nav_bg));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.cart){
            Intent intent = new Intent(HomeActivity.this,productCart.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.logout) {
           SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}