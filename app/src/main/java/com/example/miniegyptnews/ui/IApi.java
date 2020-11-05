package com.example.miniegyptnews.ui;

import com.example.miniegyptnews.ui.Models.ArticlesData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApi {

    @GET("top-headlines?country=eg&apiKey=56aa6cc3f7434d3cabb5c16932360d16")
    Call<ArticlesData> getTopHeadline();
    @GET("top-headlines?country=eg&category=business&apiKey=56aa6cc3f7434d3cabb5c16932360d16")
    Call<ArticlesData> getBusiness();
    @GET("top-headlines?country=eg&category=entertainment&apiKey=56aa6cc3f7434d3cabb5c16932360d16")
    Call<ArticlesData> getEntertainment();
    @GET("top-headlines?country=eg&category=health&apiKey=56aa6cc3f7434d3cabb5c16932360d16")
    Call<ArticlesData> getHealth();
    @GET("top-headlines?country=eg&category=science&apiKey=56aa6cc3f7434d3cabb5c16932360d16")
    Call<ArticlesData> getScience();
    @GET("top-headlines?country=eg&category=sports&apiKey=56aa6cc3f7434d3cabb5c16932360d16")
    Call<ArticlesData> getSports();
    @GET("top-headlines?country=eg&category=technology&apiKey=56aa6cc3f7434d3cabb5c16932360d16")
    Call<ArticlesData> getTechnology();
}
