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

import com.example.moviemap.Favourites;
import com.example.moviemap.MainActivity;
import com.example.moviemap.R;
import com.example.moviemap.architecture_component.MovieFavEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder>{

    private List<MovieFavEntity> movieFavEntities;
    private Context context;

    public FavAdapter(Context context) {
        this.context = context;
        movieFavEntities = new ArrayList<>();
    }

    public void update(List<MovieFavEntity> movieFavEntities){

        this.movieFavEntities = movieFavEntities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fav_movie_grid,viewGroup,false);
        return new FavViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder favViewHolder, int i) {

        Picasso.get().load(MainActivity.BASE_IMAGE_URL+movieFavEntities.get(i).getPoster_path())
                .into(favViewHolder.imageView);
        favViewHolder.likes.setText(movieFavEntities.get(i).getVote_count()+"");
        favViewHolder.ratings.setText(movieFavEntities.get(i).getVote_average()+"");
    }

    @Override
    public int getItemCount() {
        return movieFavEntities.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private ImageView imageView;
        private TextView ratings,likes;


        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.movie_view);
            imageView = itemView.findViewById(R.id.movie_img);
            ratings = itemView.findViewById(R.id.movie_ratings);
            likes = itemView.findViewById(R.id.movie_likes);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Favourites favourites = (Favourites) context;
            favourites.click(getAdapterPosition());

        }

    }


}
