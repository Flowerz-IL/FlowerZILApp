package com.flowerzapi.providers_dashboard_app.model.externalDB;

import android.app.Activity;
import android.graphics.Bitmap;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquet;
import com.flowerzapi.providers_dashboard_app.model.models.userModel.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExternalRepository {

    // Authenticate functions
    public void signIn(String email, String password, Activity currentActivity, MainRepository.CustomListener<Boolean> listener){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(currentActivity, task -> { listener.onComplete(task.isSuccessful()); });
    }
    public void signUp(String email, String password, Activity currentActivity, MainRepository.CustomListener<Boolean> listener){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(currentActivity, task -> { listener.onComplete(task.isSuccessful()); });
    }
    public FirebaseUser getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }
    public void signOut(){ FirebaseAuth.getInstance().signOut(); }
    public void changePassword(String password, MainRepository.CustomListener<Boolean> listener) {
        Objects.requireNonNull(FirebaseAuth
                .getInstance()
                .getCurrentUser())
                .updatePassword(password)
                .addOnCompleteListener(task -> listener.onComplete(task.isSuccessful()));
    }
    public void deleteCurrentUser(MainRepository.CustomListener<String> listener){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String userId = user.getUid();
        user.delete().addOnCompleteListener(task -> {
            if(task.isSuccessful()) listener.onComplete(userId);
            else listener.onComplete("");
        });
    }

    // FireStore functions
    public void addOrUpdateUser(User user, MainRepository.CustomListener<Boolean> listener) {
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore
                .collection("users")
                .document(user.getUserId())
                .set(user)
                .addOnSuccessListener(aVoid -> { listener.onComplete(true); })
                .addOnFailureListener(e -> { listener.onComplete(false); });
    }
    public void getAllUsers(MainRepository.CustomListener<List<User>> listener) {
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    List<User> users = new ArrayList<>();
                    if(task.isSuccessful())
                        for( DocumentSnapshot doc : Objects.requireNonNull(task.getResult()))
                            users.add(doc.toObject(User.class));
                    listener.onComplete(users);
                });
    }
    public void getSpecificUser(String userId, MainRepository.CustomListener<User> listener){
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore.collection("users")
                .document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    User user = null;
                    if(task.isSuccessful() && task.getResult() != null)
                        user = task.getResult().toObject(User.class);
                    listener.onComplete(user);
                });
    }
    public void deleteSpecificUser(String userId, MainRepository.CustomListener<Boolean> listener) {
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore
                .collection("users")
                .document(userId)
                .delete()
                .addOnCompleteListener(task -> listener.onComplete(task.isSuccessful()));
    }

    public void addOrUpdateBouquet(FlowerBouquet bouquet, MainRepository.CustomListener<Boolean> listener){
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore
                .collection("flowerBouquets")
                .document(bouquet.getBouquetId())
                .set(bouquet)
                .addOnSuccessListener(aVoid -> { listener.onComplete(true); })
                .addOnFailureListener(e -> { listener.onComplete(false); });
    }
    public void getAllBouquets(MainRepository.CustomListener<List<FlowerBouquet>> listener) {
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore.collection("flowerBouquets")
                .get()
                .addOnCompleteListener(task -> {
                    List<FlowerBouquet> bouquets = new ArrayList<>();
                    if(task.isSuccessful())
                        for( DocumentSnapshot doc : Objects.requireNonNull(task.getResult()))
                            bouquets.add(doc.toObject(FlowerBouquet.class));
                    listener.onComplete(bouquets);
                });
    }
    public void getSpecificBouquet(String bouquetId, MainRepository.CustomListener<FlowerBouquet> listener){
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore.collection("flowerBouquets")
                .document(bouquetId)
                .get()
                .addOnCompleteListener(task -> {
                    FlowerBouquet bouquet = null;
                    if(task.isSuccessful() && task.getResult() != null)
                        bouquet = task.getResult().toObject(FlowerBouquet.class);
                    listener.onComplete(bouquet);
                });
    }
    public void deleteSpecificBouquet(String bouquetId, MainRepository.CustomListener<Boolean> listener) {
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        fireStore
                .collection("flowerBouquets")
                .document(bouquetId)
                .delete()
                .addOnCompleteListener(task -> listener.onComplete(task.isSuccessful()));
    }

    // Storage functions
    public void uploadImage(Bitmap image, String name, MainRepository.CustomListener<String> listener){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference imagesRef = storage.getReference().child("images").child(name);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = imagesRef.putBytes(data);
        uploadTask
            .addOnFailureListener( exception -> listener.onComplete(null))
            .addOnSuccessListener( taskSnapshot ->
                    imagesRef.getDownloadUrl().addOnSuccessListener( uri -> listener.onComplete(uri.toString())));
    }
}
