package com.example.dell.myonlineapplicationmain.models;

public class Product {

    String title,shortdesc;
    double rating;
    double price;
    int image;

    String image_url;
//    public Product(int id, String title, String image) {
//        this.id = id;
//        this.title = title;
//        this.image = image;
//    }

    public Product(String title, String shortdesc, double rating, double price, String image_url) {
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image_url = image_url;
    }

    public Product(String title, String shortdesc, double rating, double price, int image) {
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
