package com.example.moviemap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviemap.MainActivity;
import com.example.moviemap.Movie_info;
import com.example.moviemap.R;
import com.example.moviemap.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder> {

    private ArrayList<Movie> movies;
    private Context context;
    private final int width;

    public RecommendationAdapter(Context context,int width) {
        this.movies = new ArrayList<>();
        this.context = context;
        this.width = width;

    }

    public void update(ArrayList<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecommendationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_grid, viewGroup, false);

        v.getLayoutParams().width = this.width;
        return new RecommendationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationViewHolder recommendationViewHolder, int i) {

        recommendationViewHolder.title.setText(movies.get(i).getTitle());
        recommendationViewHolder.setImage(movies.get(i).getPoster_path());
        recommendationViewHolder.likes.setText(movies.get(i).getVote_count() + "");
        recommendationViewHolder.ratings.setText(movies.get(i).getVote_average() + "");

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class RecommendationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private ImageView img;
        private TextView title, likes, ratings;


        public RecommendationViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.movie_view);
            img = itemView.findViewById(R.id.movie_img);
            title = itemView.findViewById(R.id.movie_title);
            likes = itemView.findViewById(R.id.movie_likes);
            ratings = itemView.findViewById(R.id.movie_ratings);

            //register a listner
            cardView.setOnClickListener(this);
        }

        private void setImage(String url) {

            Picasso.get().
                    load(MainActivity.BASE_IMAGE_URL + url)
                    .into(img);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Movie_info movie_info = (Movie_info) context;
            movie_info.click(position);

        }


    }

}
