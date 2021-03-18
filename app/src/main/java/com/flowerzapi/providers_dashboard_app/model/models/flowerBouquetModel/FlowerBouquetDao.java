package com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FlowerBouquetDao {

    @Query("SELECT * FROM flower_bouquets ORDER BY title")
    LiveData<List<FlowerBouquet>> getAllBouquets();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBouquets(FlowerBouquet... flowerBouquets);

    @Update
    void updateBouquet(FlowerBouquet flowerBouquet);

    @Delete
    void deleteBouquet(FlowerBouquet flowerBouquet);

    @Query("DELETE FROM flower_bouquets WHERE `id`=:flowerBouquetId")
    void deleteBouquetFromId(String flowerBouquetId);
}
