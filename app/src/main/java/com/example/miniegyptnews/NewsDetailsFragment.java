package com.example.miniegyptnews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.miniegyptnews.ui.Models.Article;
import com.google.android.material.button.MaterialButton;

import java.util.concurrent.ExecutionException;


public class NewsDetailsFragment extends Fragment {

    ImageView newsImage;
    TextView newsTitle;
    TextView newsDetails;
    MaterialButton visitSiteBtn;
    Article article;
    ProgressDialog  progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news_details, container, false);
        newsImage = v.findViewById(R.id.news_details_iv);
        newsTitle = v.findViewById(R.id.news_title_tv);
        newsDetails = v.findViewById(R.id.news_details_tv);
        visitSiteBtn = v.findViewById(R.id.vist_site_btn);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = new ProgressDialog(requireContext());

        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
        getClickedNews();
        visitSiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireContext().startActivity(new Intent(Intent.ACTION_VIEW,
                      Uri.parse(article.getUrl())));
            }
        });


    }
    private String getFullDetials(String url,String sourceName){
        ReadHTML x = new ReadHTML(url,sourceName,requireContext());
        String res = "";
        try {
            res = x.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }
    private void getClickedNews() {

        Bundle args = getArguments();
        if (args != null) {
            article = (Article) args.getSerializable("news");
            newsTitle.setText(article.getTitle());
            Glide.with(requireContext()).load(article.getUrlToImage()).apply(RequestOptions.centerCropTransform()).into(newsImage);


            try {
                newsDetails.setText(getFullDetials(article.getUrl(),article.getSources().getName()));
                if(newsDetails.getText().equals("")) newsDetails.setText(article.getDescription());

            }catch (Exception e){
                newsDetails.setText(article.getDescription());
            }
            progressBar.dismiss();



        }

    }
}