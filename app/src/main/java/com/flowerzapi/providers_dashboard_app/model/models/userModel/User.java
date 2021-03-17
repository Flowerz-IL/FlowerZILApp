package com.flowerzapi.providers_dashboard_app.model.models.userModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "users")
public class User {

    //Data
    @PrimaryKey()
    @ColumnInfo(name="id")
    @NonNull
    private String userId;
    private String email;
    @ColumnInfo(name="first_name")
    private String firstName;
    @ColumnInfo(name="last_name")
    private String lastName;
    @ColumnInfo(name="store_name")
    private String storeName;
    @ColumnInfo(name="phone_number")
    private String phoneNumber;
    @ColumnInfo(name="last_updated_at")
    private long lastUpdated;

    // Constructor
    public User(){}

    public User(@NotNull String userId, String email, String firstName, String lastName, String storeName, String phoneNumber) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.storeName = storeName;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public @NotNull String getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStoreName() { return storeName; }
    public String getPhoneNumber() { return phoneNumber; }
    public long getLastUpdated() { return this.lastUpdated; }

    // Setters
    public void setUserId(@NotNull String userId) {this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setLastUpdated(long lastUpdated) { this.lastUpdated = lastUpdated; }

    // Helpers
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("email", email);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("storeName", storeName);
        map.put("phoneNumber", phoneNumber);
        map.put("lastUpdated", FieldValue.serverTimestamp());
        return map;
    }
    public void fromMap(Map<String, Object> map) {
        setUserId((String) map.get("userId"));
        setEmail((String) map.get("email"));
        setFirstName((String) map.get("firstName"));
        setLastName((String) map.get("lastName"));
        setStoreName((String) map.get("storeName"));
        setPhoneNumber((String) map.get("phoneNumber"));
        setLastUpdated(((Timestamp) map.get("lastUpdated")).toDate().getTime());
    }
}
