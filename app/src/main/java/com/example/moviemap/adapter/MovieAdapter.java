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
import com.example.moviemap.R;
import com.example.moviemap.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> movies;
    private Context context;
    private final int width;

    public MovieAdapter(Context context,int width) {
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
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_grid, viewGroup, false);

        v.getLayoutParams().width = this.width;
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {

        movieViewHolder.title.setText(movies.get(i).getTitle());
        movieViewHolder.setImage(movies.get(i).getPoster_path());
        movieViewHolder.likes.setText(movies.get(i).getVote_count() + "");
        movieViewHolder.ratings.setText(movies.get(i).getVote_average() + "");

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private ImageView img;
        private TextView title, likes, ratings;


        public MovieViewHolder(@NonNull View itemView) {
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
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.click(position);

        }


    }

}
