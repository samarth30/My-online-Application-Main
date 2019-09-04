package com.example.dell.myonlineapplicationmain.models;

public class ExampleItem {
    private String mImageUrl;
    private String mTitle,mShortDescription;
    private int mRating,mPrice;

    public ExampleItem(String imageUrl, String title, int rating, int price, String shortDescription){
        mImageUrl = imageUrl;
        mTitle = title;
        mRating = rating;
        mPrice = price;
        mShortDescription = shortDescription;
    }

    public String getImageUrl(){
        return  mImageUrl;

    }
    public String getmShortDescription(){
        return  mShortDescription;

    }
    public int getPrice(){
        return  mPrice;

    }

    public String getmTitle(){
        return mTitle;
    }
    public int getRating(){
        return mRating;
    }

}
