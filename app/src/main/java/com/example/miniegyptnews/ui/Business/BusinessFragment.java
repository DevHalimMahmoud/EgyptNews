package com.example.miniegyptnews.ui.Business;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniegyptnews.R;
import com.example.miniegyptnews.ui.API.ApiClient;
import com.example.miniegyptnews.ui.Models.ArticlesData;
import com.example.miniegyptnews.ui.API.IApi;
import com.example.miniegyptnews.ui.API.ItemArrayAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessFragment extends Fragment {
    RecyclerView recyclerView;
    ArticlesData articlesData;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_business, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        IApi apiService = ApiClient.getClient().create(IApi.class);
        Call<ArticlesData> call = apiService.getBusiness();

        call.enqueue(new Callback<ArticlesData>() {
            @Override
            public void onResponse(Call<ArticlesData> call, Response<ArticlesData> response) {

                assert response.body() != null;
                ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(R.layout.news_item, response.body(), new ItemArrayAdapter.GoToDetails() {
                    @Override
                    public void onNewsClick(View view, int position) {
                        Bundle e = new Bundle();
                        e.putSerializable("news",response.body().getArticles().get(position));
                        Navigation.findNavController(view).navigate(R.id.action_nav_business_to_newsDetailsFragment,e);
                    }
                });
                articlesData=new ArticlesData(response.body().getStatus(),response.body().getTotalResults(),response.body().getArticles());

                recyclerView.setAdapter(itemArrayAdapter);
            }

            @Override
            public void onFailure(Call<ArticlesData> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString(),t);
            }
        });
        return root;
    }
}