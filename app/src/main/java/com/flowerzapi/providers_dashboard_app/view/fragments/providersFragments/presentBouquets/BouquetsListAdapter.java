package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.presentBouquets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.model.MainRepository;
import com.flowerzapi.providers_dashboard_app.model.models.flowerBouquetModel.FlowerBouquet;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class BouquetsListAdapter extends RecyclerView.Adapter<BouquetsListAdapter.BouquetsListViewHolder> {

    // Data
    private List<FlowerBouquet> data;
    private String currentUserId;
    private Context context;
    public boolean allowEdit;

    // Constructor
    public BouquetsListAdapter(Context context, String currentUserId){
        this.context = context;
        this.currentUserId = currentUserId;
        this.allowEdit = false;
    }

    // Setter
    public void setData(List<FlowerBouquet> data){ this.data = data;}

    public void setAllowEdit(Boolean isAllowed){ this.allowEdit = isAllowed;}

    // Overrides
    @NonNull
    @Override
    public BouquetsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bouquet_editable_item, parent, false);
        return new BouquetsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BouquetsListViewHolder holder, int position) {
        if(data == null) return;
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() { return data != null ? data.size() : 0; }

    // View Holder
    public class BouquetsListViewHolder extends RecyclerView.ViewHolder {

        // Data
        TextView titleTV, descriptionTV, storeNameTV;
        ImageView flowerImage;
        ImageButton editIB, deleteIB, contactUsBT;
        FlowerBouquet bouqet;

        // Constructor
        public BouquetsListViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialise view items
            titleTV = itemView.findViewById(R.id.title_bouquet_editable);
            descriptionTV = itemView.findViewById(R.id.description_bouquet_editable);
            storeNameTV = itemView.findViewById(R.id.store_name_editable_text);
            flowerImage = itemView.findViewById(R.id.img_bouquet_editable);
            editIB = itemView.findViewById(R.id.edit_bouquet_editable);
            deleteIB = itemView.findViewById(R.id.delete_bouquet_editable);
            contactUsBT = itemView.findViewById(R.id.contact_us_bouquet_editable);

            // Set buttons actions
            editIB.setOnClickListener(moveToEdit);
            deleteIB.setOnClickListener(deleteItem);
            contactUsBT.setOnClickListener(contactProvider);

            editIB.setVisibility(View.GONE);
            deleteIB.setVisibility(View.GONE);
        }

        // Helper
        public void setData(FlowerBouquet bouqet){
            this.bouqet = bouqet;
            titleTV.setText(bouqet.getBouquetTitle());
            descriptionTV.setText(bouqet.getBouquetDescription());
            storeNameTV.setText(bouqet.getStoreName());
            Picasso.get()
                    .load(bouqet.getBouquetImageUrl())
                    .placeholder(R.drawable.defaultbouquet)
                    .error(R.drawable.defaultbouquet)
                    .into(flowerImage);
            if(bouqet.getUserId().equals(currentUserId) && allowEdit){
                editIB.setVisibility(View.VISIBLE);
                deleteIB.setVisibility(View.VISIBLE);
            }
        }

        // On Click Listeners
        View.OnClickListener moveToEdit = view -> Navigation.findNavController(view).navigate(providerItemsFragmentDirections
                .providersItemsToEditBouqet(bouqet.getBouquetId(),bouqet.getBouquetImageUrl(), bouqet.getBouquetTitle(),bouqet.getBouquetDescription()));
        View.OnClickListener deleteItem = view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete flower bouquet with the title" + bouqet.getBouquetTitle())
                    .setMessage("Are you sure you want to delete this flower bouquet?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        MainRepository.getInstance().deleteSpecificBouquet(bouqet.getBouquetId(), isSuccessful -> {});
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        };
        View.OnClickListener contactProvider = view -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+bouqet.getUserPhone()));
            context.startActivity(callIntent);
        };
    }
}
