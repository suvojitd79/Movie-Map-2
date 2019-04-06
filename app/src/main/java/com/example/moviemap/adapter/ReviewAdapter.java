package com.example.moviemap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviemap.R;
import com.example.moviemap.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    private Context context;
    private ArrayList<Review> reviews;

    public ReviewAdapter(Context context) {
        this.context = context;
        reviews = new ArrayList<>();
    }

    public void update(ArrayList<Review> reviews){

        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.review_grid,viewGroup,false);
        return new ReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int i) {

        reviewViewHolder.content.setText(reviews.get(i).getContent());
        reviewViewHolder.author.setText(reviews.get(i).getAuthor());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView content,author;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.movie_review_author);
            content = itemView.findViewById(R.id.movie_review);
        }
    }

}
