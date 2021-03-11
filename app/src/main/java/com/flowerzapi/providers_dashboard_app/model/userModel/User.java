package com.flowerzapi.providers_dashboard_app.model.userModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "users")
public class User {

    //Data
    @PrimaryKey()
    @ColumnInfo(name="id")
    @NonNull
    private final String userId;
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
    public void setEmail(String email) { this.email = email; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setLastUpdated(long lastUpdated) { this.lastUpdated = lastUpdated; }

}
