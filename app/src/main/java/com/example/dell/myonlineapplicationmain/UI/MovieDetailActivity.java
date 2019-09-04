package com.example.dell.myonlineapplicationmain.UI;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.myonlineapplicationmain.R;
import com.example.dell.myonlineapplicationmain.adapters.MovieAdapter;
import com.example.dell.myonlineapplicationmain.adapters.MovieItemClickListener;
import com.example.dell.myonlineapplicationmain.adapters.RecyclerItemClickListener;
import com.example.dell.myonlineapplicationmain.adapters.SliderPagerAdapter;
import com.example.dell.myonlineapplicationmain.models.Movie;
import com.example.dell.myonlineapplicationmain.models.Product;
import com.example.dell.myonlineapplicationmain.models.Slide;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity{

    private List<Slide> lstSlides;
    private ViewPager slidePager;
    private TabLayout indicator;
    private List<Product> lstCast;
    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private RecyclerView RvCast;
    TextView rating_DetailPage,shortDesc_DetailPage,price_DetailPage,title_DetailPage;
    ImageView imageView_DetailPage;
    Button AddToCart,BuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        // iniViews
        iniViews();

        lstCast = new ArrayList<>();
        initCast();

        slidePager = findViewById(R.id.imageView4);
        indicator = findViewById(R.id.indicator);

        lstSlides = new ArrayList<>();
        ListSlidesnames();
        SliderPagerAdapter slideAdapter = new SliderPagerAdapter(this,lstSlides);
        slidePager.setAdapter(slideAdapter);
        // dots indicator
        indicator.setupWithViewPager(slidePager,true);


        MovieAdapter adapter = new MovieAdapter(this,lstCast);
        RvCast.setAdapter(adapter);
        RvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false));

        RvCast.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Intent intent = new Intent(MovieDetailActivity.this, MovieDetailActivity.class);
                        Product currentItem = lstCast.get(position);
                        intent.putExtra("title",currentItem.getTitle());
                        intent.putExtra("imgURL",currentItem.getImage());
                        intent.putExtra("imgCover",currentItem.getImage());
                        intent.putExtra("rating",currentItem.getRating());
                        intent.putExtra("price",currentItem.getPrice());
                        intent.putExtra("short_desc",currentItem.getShortdesc());

                        startActivity(intent);
                    }
                })
        );
    }

    private void initCast() {
        lstCast.add(new Product("phone","very useful it is good",4.7,7200,R.drawable.b));
        lstCast.add(new Product("phone","very useful it is good",4.7,7200,R.drawable.b));
        lstCast.add(new Product("phone","very useful it is good",4.7,7200,R.drawable.b));
        lstCast.add(new Product("phone","very useful it is good",4.7,7200,R.drawable.b));
        lstCast.add(new Product("phone","very useful it is good",4.7,7200,R.drawable.b));
        lstCast.add(new Product("phone","very useful it is good",4.7,7200,R.drawable.b));
        lstCast.add(new Product("phone","very useful it is good",4.7,7200,R.drawable.b));
        lstCast.add(new Product("phone","very useful it is good",4.7,7200,R.drawable.b));
    }


    void iniViews(){
        String titleResourceId = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int ratingResourceId = getIntent().getExtras().getInt("rating");
        int priceResourceId = getIntent().getExtras().getInt("price");
        String shortDescResourceId = getIntent().getExtras().getString("short_desc");

        price_DetailPage = findViewById(R.id.textViewPrice);
        price_DetailPage.setText(String.valueOf(priceResourceId));

        title_DetailPage = findViewById(R.id.textViewtitle);
        title_DetailPage.setText(titleResourceId);

        rating_DetailPage = findViewById(R.id.textViewRating);
        rating_DetailPage.setText(String.valueOf(ratingResourceId));


        AddToCart = findViewById(R.id.addtocart);
        BuyNow = findViewById(R.id.buyNow);
        RvCast = findViewById(R.id.rv_cast);


    }

    private void ListSlidesnames() {
        lstSlides.add(new Slide(R.drawable.e));
        lstSlides.add(new Slide(R.drawable.a));
        lstSlides.add(new Slide(R.drawable.g));
        lstSlides.add(new Slide(R.drawable.b));
    }
    public void onMovieClick(Movie movie, ImageView movieImageView) {

        // here we send moview information to detail actiivty
        //also we will create the transition bew the two activty

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MovieDetailActivity.this,
                movieImageView,"sharedName");

        startActivity(intent,options.toBundle());
        Toast.makeText(this,"the item " + movie.getTitle() + " is Clicked",Toast.LENGTH_LONG).show();
    }
}