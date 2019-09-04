package com.example.dell.myonlineapplicationmain.adapters;

import android.widget.ImageView;

import com.example.dell.myonlineapplicationmain.models.Movie;


public interface MovieItemClickListener {
    // we will need the image to make the shared bew activites
    void onMovieClick(Movie movie, ImageView movieImageView);
}
