package com.example.dell.myonlineapplicationmain.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.myonlineapplicationmain.R;
import com.example.dell.myonlineapplicationmain.models.ExampleItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;

    public ExampleAdapter(Context context,ArrayList<ExampleItem> exampleList){
        mContext = context;
        mExampleList = exampleList;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_main_vertical,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        String imageUrl = currentItem.getImageUrl();
        String title = currentItem.getmTitle();
        int price = currentItem.getPrice();
        String shortDesc = currentItem.getmShortDescription();
        int rating = currentItem.getRating();

        holder.mTextViewTitle.setText(title);
        holder.mTextViewRating.setText("* " + rating);
        holder.mTextViewshortDescription.setText(shortDesc);
        holder.mTextViewPrice.setText("$ "+price);
        Picasso.with(mContext).load(imageUrl).into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewRating,mTextViewPrice,mTextViewshortDescription;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.product_imageView);
            mTextViewRating = itemView.findViewById(R.id.product_Rating);
            mTextViewTitle = itemView.findViewById(R.id.product_title);
            mTextViewPrice = itemView.findViewById(R.id.product_Price);
            mTextViewshortDescription = itemView.findViewById(R.id.product_ShortDesc);

        }
    }
}
