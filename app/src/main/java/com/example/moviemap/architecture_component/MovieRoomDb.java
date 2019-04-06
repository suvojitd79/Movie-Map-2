package com.example.moviemap.architecture_component;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {MovieFavEntity.class},version = 1)
public abstract class MovieRoomDb extends RoomDatabase {

    public abstract MovieFavDao getDao();

    private static MovieRoomDb movieRoomDb;

    public static synchronized MovieRoomDb getMovieRoomDb(Context context) {

        if(movieRoomDb==null){

            movieRoomDb = Room.databaseBuilder(context.getApplicationContext(),
                    MovieRoomDb.class,"movie_map")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();

        }

        return movieRoomDb;
    }

    private static RoomDatabase.Callback callback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };


}
