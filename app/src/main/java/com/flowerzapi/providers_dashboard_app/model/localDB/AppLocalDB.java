package com.flowerzapi.providers_dashboard_app.model.localDB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.flowerzapi.providers_dashboard_app.model.flowerBouquetModel.FlowerBouquet;
import com.flowerzapi.providers_dashboard_app.model.flowerBouquetModel.FlowerBouquetDao;
import com.flowerzapi.providers_dashboard_app.model.userModel.User;
import com.flowerzapi.providers_dashboard_app.model.userModel.UserDao;

@Database(entities = {FlowerBouquet.class, User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppLocalDB extends RoomDatabase {

    // Data
    private static AppLocalDB instance;
    public abstract FlowerBouquetDao flowerBouquetDao();
    public abstract UserDao userDao();

    // Get Instance
    public static synchronized AppLocalDB getInstance(Context context) {
        if(instance == null)
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), AppLocalDB.class, "flowerZIL_database")
                    .fallbackToDestructiveMigration()
                    .build();

        return instance;
    }
}
