package com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    @ColumnInfo(name = "user_id")
    private String userId;
    @ColumnInfo(name = "user_phone")
    private String userPhone;
    @ColumnInfo(name = "store_name")
    private String storeName;
    @ColumnInfo(name="image_url")
    private String bouquetImageUrl;
    @ColumnInfo(name="title")
    private String bouquetTitle;
    @ColumnInfo(name="description")
    private String bouquetDescription;
    @ColumnInfo(name="last_updated_at")
    private long lastUpdated;
    @ColumnInfo(name="is_deleted")
    private boolean isDeleted;

    // Constructors
    public FlowerBouquet(){ this.bouquetId = UUID.randomUUID().toString(); this.isDeleted= false;}
    public FlowerBouquet(@NotNull String id){ this.bouquetId = id; this.isDeleted= false;}

    // Getters
    public @NotNull String getBouquetId() { return this.bouquetId; }
    public String getUserId() { return userId; }
    public String getUserPhone() { return userPhone; }
    public String getStoreName() { return storeName; }
    public String getBouquetImageUrl() { return this.bouquetImageUrl; }
    public String getBouquetTitle() { return this.bouquetTitle; }
    public String getBouquetDescription() { return this.bouquetDescription; }
    public long getLastUpdated() { return this.lastUpdated; }
    public boolean isDeleted() { return isDeleted; }

    // Setters
    public void setBouquetId(@NotNull String bouquetId) { this.bouquetId = bouquetId; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone;}
    public void setStoreName(String storeName) { this.storeName = storeName; }
    public void setBouquetImageUrl(String bouquetImageUrl) { this.bouquetImageUrl = bouquetImageUrl; }
    public void setBouquetTitle(String bouquetTitle) { this.bouquetTitle = bouquetTitle; }
    public void setBouquetDescription(String bouquetDescription) { this.bouquetDescription = bouquetDescription; }
    public void setLastUpdated(long lastUpdated) { this.lastUpdated = lastUpdated; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }

    // Helpers
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bouquetId", bouquetId);
        map.put("userId", userId);
        map.put("userPhone", userPhone);
        map.put("storeName", storeName);
        map.put("bouquetImageUrl", bouquetImageUrl);
        map.put("bouquetTitle", bouquetTitle);
        map.put("bouquetDescription", bouquetDescription);
        map.put("lastUpdated", FieldValue.serverTimestamp());
        map.put("isDeleted",isDeleted);
        return map;
    }
    public void fromMap(Map<String, Object> map) {
        setBouquetId((String) map.get("bouquetId"));
        setUserId((String) map.get("userId"));
        setUserPhone((String) map.get("userPhone"));
        setStoreName((String) map.get("storeName"));
        setBouquetImageUrl((String) map.get("bouquetImageUrl"));
        setBouquetTitle((String) map.get("bouquetTitle"));
        setBouquetDescription((String) map.get("bouquetDescription"));
        setLastUpdated(((Timestamp) map.get("lastUpdated")).getSeconds());
        setDeleted(((boolean) map.get("isDeleted")));
    }
}
