package com.flowerzapi.providers_dashboard_app.model.flowerBouquetModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

/**
 * Represent singular flower bouquet with image,
 * title, and description.
 */
@Entity(tableName = "flower_bouquets")
public class FlowerBouquet {

    // Data
    @PrimaryKey()
    @ColumnInfo(name = "id")
    @NonNull
    private String bouquetId;
    @ColumnInfo(name="image_url")
    private String bouquetImageUrl;
    @ColumnInfo(name="title")
    private String bouquetTitle;
    @ColumnInfo(name="description")
    private String bouquetDescription;
    @ColumnInfo(name="last_updated_at")
    private long lastUpdated;

    // Constructors
    public FlowerBouquet(){
        this.bouquetId = UUID.randomUUID().toString();
    }
    public FlowerBouquet(String bouquetImageUrl, String bouquetTitle, String bouquetDescription) {
        this.bouquetId = UUID.randomUUID().toString();
        this.bouquetImageUrl = bouquetImageUrl;
        this.bouquetTitle = bouquetTitle;
        this.bouquetDescription = bouquetDescription;
    }
    public FlowerBouquet(@org.jetbrains.annotations.NotNull String bouquetId, String bouquetImageUrl, String bouquetTitle, String bouquetDescription) {
        this.bouquetId = bouquetId;
        this.bouquetImageUrl = bouquetImageUrl;
        this.bouquetTitle = bouquetTitle;
        this.bouquetDescription = bouquetDescription;
    }

    // Getters
    public String getBouquetId() { return this.bouquetId; }
    public String getBouquetImageUrl() { return this.bouquetImageUrl; }
    public String getBouquetTitle() { return this.bouquetTitle; }
    public String getBouquetDescription() { return this.bouquetDescription; }
    public long getLastUpdated() { return this.lastUpdated; }

    // Setters
    public void setBouquetId(String bouquetId) { this.bouquetId = bouquetId; }
    public void setBouquetImageUrl(String bouquetImageUrl) { this.bouquetImageUrl = bouquetImageUrl; }
    public void setBouquetTitle(String bouquetTitle) { this.bouquetTitle = bouquetTitle; }
    public void setBouquetDescription(String bouquetDescription) { this.bouquetDescription = bouquetDescription; }
    public void setLastUpdated(long lastUpdated) { this.lastUpdated = lastUpdated; }
}
