package com.example.dell.myonlineapplicationmain.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.myonlineapplicationmain.R;
import com.example.dell.myonlineapplicationmain.models.Product;

import java.util.List;

public class CartAdapterVertical extends RecyclerView.Adapter<CartAdapterVertical.MyViewHolder>{

    Context context;
    List<Product> mData;

    public CartAdapterVertical(Context context, List<Product> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart,viewGroup,false);

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
        Button btnRemove,btnBuy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPrice = itemView.findViewById(R.id.priceCart);
            textViewRating = itemView.findViewById(R.id.ratingCart);
            textViewTitle = itemView.findViewById(R.id.titleCart);
            textViewShortDesc = itemView.findViewById(R.id.descriptionCart);
            imageView = itemView.findViewById(R.id.imageViewCart);
            btnBuy = itemView.findViewById(R.id.buycart);
            btnRemove = itemView.findViewById(R.id.removeCart);
        }
    }
}
