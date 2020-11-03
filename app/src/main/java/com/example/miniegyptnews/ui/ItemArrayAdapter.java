package com.example.miniegyptnews.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.miniegyptnews.R;

import java.util.ArrayList;

public class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {
    private int listItemLayout;
    private ArticlesData articlesData;
    private View view;


    public ItemArrayAdapter(int layoutId, ArticlesData articlesData) {
        listItemLayout = layoutId;
        this.articlesData = articlesData;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {

        TextView title = holder.title;
        TextView info = holder.info;
        TextView source_name = holder.source;
        TextView date = holder.date;

        ArrayList<Article> articles = articlesData.getArticles();
        Glide.with(view.getContext()).load(articles.get(listPosition).getUrlToImage()).apply(RequestOptions.centerCropTransform()).into(holder.imageView);
        title.setText(articles.get(listPosition).getTitle());
        info.setText(articles.get(listPosition).getDescription());
        source source = articles.get(listPosition).getSources();
        source_name.setText(source.getName());
        String date_raw = articles.get(listPosition).getPublishedAt().substring(0, 10);
        date.setText(date_raw);
        String url = articles.get(listPosition).getUrl();
        url=holder.url;

    }


    @Override
    public int getItemCount() {

        if (articlesData.getArticles() != null) {
            return articlesData.getArticles().size();
        }
        return 0;

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView title, info, source, date;
        String url;
        private Context context =itemView.getContext();;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            itemView.setOnClickListener(this);
            itemView.getContext();
            imageView = itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            info = (TextView) itemView.findViewById(R.id.info);
            source = (TextView) itemView.findViewById(R.id.source);
            date = (TextView) itemView.findViewById(R.id.date);
        }

        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + title.getText());
            ArrayList<Article> articles= articlesData.getArticles();


            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(articles.get(getLayoutPosition()).getUrl())));
        }


    }
}
