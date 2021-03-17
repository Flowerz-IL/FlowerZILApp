package com.flowerzapi.providers_dashboard_app.model.localDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.flowerzapi.providers_dashboard_app.MyApplication;
import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquet;
import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquetDao;
import com.flowerzapi.providers_dashboard_app.model.models.userModel.User;
import com.flowerzapi.providers_dashboard_app.model.models.userModel.UserDao;

@Database(entities = {FlowerBouquet.class, User.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class AppLocalDB extends RoomDatabase {

    // Data
    private static AppLocalDB instance;
    public abstract FlowerBouquetDao flowerBouquetDao();
    public abstract UserDao userDao();

    // Get Instance
    public static synchronized AppLocalDB getInstance() {
        if(instance == null)
            instance = Room
                    .databaseBuilder(MyApplication.context, AppLocalDB.class, "flowerZIL_database")
                    .fallbackToDestructiveMigration()
                    .build();

        return instance;
    }
}
