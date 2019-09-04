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
import com.example.dell.myonlineapplicationmain.models.Movie;
import com.example.dell.myonlineapplicationmain.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<Product> mData;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context context, List<Product> mData) {
        this.context = context;
        this.mData = mData;
    }

    public MovieAdapter(Context context, List<Product> mData, MovieItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_horizontal,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textViewTitle.setText(mData.get(i).getTitle());
        myViewHolder.imageView.setImageResource(mData.get(i).getImage());
        myViewHolder.textViewPrice.setText(String.valueOf(mData.get(i).getPrice()));
        myViewHolder.textViewRating.setText(String.valueOf(mData.get(i).getRating()));
        myViewHolder.textViewShortDesc.setText(mData.get(i).getShortdesc());

//        String ImageUrl = mData.get(i).getImage_url();
//
//        Picasso.with(context).load(ImageUrl).into(myViewHolder.imageView);

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