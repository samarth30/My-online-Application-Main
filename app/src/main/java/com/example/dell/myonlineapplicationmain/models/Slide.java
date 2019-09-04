package com.example.dell.myonlineapplicationmain.models;

public class Slide {
    private int Image;
    private String Title;

    public Slide(int image, String title) {
        Image = image;
        Title = title;
    }
    public Slide(int image){
        this.Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
