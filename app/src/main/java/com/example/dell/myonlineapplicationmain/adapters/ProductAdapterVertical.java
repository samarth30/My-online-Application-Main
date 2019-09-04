package com.example.dell.myonlineapplicationmain.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.myonlineapplicationmain.R;
import com.example.dell.myonlineapplicationmain.models.Product;

import java.util.List;

public class ProductAdapterVertical extends RecyclerView.Adapter<ProductAdapterVertical.MyViewHolder>{
    Context context;
    List<Product> mData;


    public ProductAdapterVertical(Context context, List<Product> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_vertical,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textViewTitle.setText(mData.get(i).getTitle());
        myViewHolder.imageView.setImageResource(mData.get(i).getImage());
        myViewHolder.textViewPrice.setText(String.valueOf(mData.get(i).getPrice()));
        myViewHolder.textViewRating.setText(String.valueOf(mData.get(i).getRating()));
        myViewHolder.textViewShortDesc.setText(mData.get(i).getShortdesc());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewtitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);

//            itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);
//                }
//            });
        }
    }
}
