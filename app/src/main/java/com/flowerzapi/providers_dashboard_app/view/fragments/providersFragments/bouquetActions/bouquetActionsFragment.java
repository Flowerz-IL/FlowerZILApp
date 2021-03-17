package com.flowerzapi.providers_dashboard_app.view.fragments.providersFragments.bouquetActions;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.flowerzapi.providers_dashboard_app.R;
import com.flowerzapi.providers_dashboard_app.util.HelperClass;
import com.flowerzapi.providers_dashboard_app.util.Validation;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class bouquetActionsFragment extends Fragment {

    // Data
    static final int REQUEST_IMAGE_CAPTURE = 0;
    static final int REQUEST_IMAGE_GALLERY = 1;
    protected ProgressBar Loader;
    protected TextView textView;
    protected AddBouquetViewModel viewModel;
    protected ImageView bouquetIV;
    protected ImageButton editImageBT;
    protected Button addBT, cancelBT;
    protected EditText titleET, descriptionET;

    // Override
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialise viewModel
        viewModel = new ViewModelProvider(this).get(AddBouquetViewModel.class);

        // Initialise view items
        textView = view.findViewById(R.id.add_bouquet_tv);
        Loader = view.findViewById(R.id.loader_add_bouquet);
        bouquetIV = view.findViewById(R.id.bouquet_image_add_bouquet);
        editImageBT = view.findViewById(R.id.edit_image_add_bouquet);
        addBT = view.findViewById(R.id.add_bt_add_bouquet);
        cancelBT = view.findViewById(R.id.cancel_bt_add_bouquet);
        titleET = view.findViewById(R.id.title_add_bouquet_et);
        descriptionET = view.findViewById(R.id.description_add_bouquet_et);

        // Set buttons actions
        editImageBT.setOnClickListener(lunchCamera);
        addBT.setOnClickListener(addBouquet);
        cancelBT.setOnClickListener(moveToMyItems);

        Loader.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bouquet_actions_fragment, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    if (resultCode == RESULT_OK && data != null)
                        bouquetIV.setImageBitmap((Bitmap) data.getExtras().get("data"));
                    break;
                case REQUEST_IMAGE_GALLERY:
                    if (resultCode != RESULT_OK || data == null) break;
                    Uri selectedImage =  data.getData();
                    if (selectedImage == null) break;
                    try (ParcelFileDescriptor pfd = getActivity().getContentResolver().openFileDescriptor(selectedImage, "r")) {
                        if (pfd != null)
                            bouquetIV.setImageBitmap(BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor()));
                    } catch (Exception ignored) {}
                    break;
            }
        }
    }

    // On click listeners
    View.OnClickListener moveToMyItems = view -> HelperClass.navigateToFragment(view, -1);
    @SuppressLint("QueryPermissionsNeeded")
    View.OnClickListener lunchCamera = view -> selectImage();
    View.OnClickListener addBouquet = view -> {
        Loader.setVisibility(View.VISIBLE);
        addBT.setVisibility(View.GONE);
        cancelBT.setVisibility(View.GONE);
        String title = titleET.getText().toString();
        String description = descriptionET.getText().toString();
        Bitmap image = ((BitmapDrawable) bouquetIV.getDrawable()).getBitmap();
        if(!validateInputData(title,description)) return;
        viewModel.addBouquet(title, description, image, isSuccessful -> {
            Loader.setVisibility(View.GONE);
            addBT.setVisibility(View.VISIBLE);
            cancelBT.setVisibility(View.VISIBLE);
            if(isSuccessful) HelperClass.navigateToFragment(view, -1);
            else HelperClass.alertMessage(getActivity(), "Something went wrong please try again later");
        });
    };

    // Helpers
    protected boolean validateInputData(String title, String description) {
        boolean res = true;

        if(!Validation.validateShortText(title)){
            titleET.setError("title - string length between 2 to 25");
            res = false;
        }

        if(!Validation.validateLongText(description)){
            descriptionET.setError("description - string length between 4 to 100");
            res = false;
        }

        if(!res) HelperClass.alertMessage(getActivity(), "fix the errors before submitting");
        return res;
    }
    protected void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose your bouquet picture");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Take Photo"))
                startActivityForResult(new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE);
            else if (options[item].equals("Choose from Gallery"))
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI) , REQUEST_IMAGE_GALLERY);
            else if (options[item].equals("Cancel"))
                dialog.dismiss();
        });
        builder.show();
    }
}