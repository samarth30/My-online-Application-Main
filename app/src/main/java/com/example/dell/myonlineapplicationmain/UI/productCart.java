package com.example.dell.myonlineapplicationmain.UI;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.example.dell.myonlineapplicationmain.R;
import com.example.dell.myonlineapplicationmain.adapters.CartAdapterVertical;
import com.example.dell.myonlineapplicationmain.models.Product;

import java.util.ArrayList;
import java.util.List;

public class productCart extends AppCompatActivity {

    List<Product> cartItems;
    RecyclerView cartRecyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cartItems = new ArrayList<>();

        cartRecyclerview = findViewById(R.id.cart);
        ListRecycleMovies();

        CartAdapterVertical adapter = new CartAdapterVertical(this,cartItems);
        cartRecyclerview.setAdapter(adapter);
        cartRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));

    }

    private void ListRecycleMovies() {
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
        cartItems.add(new Product("phone","very useful",4.7,7200,R.drawable.b));
    }

}
