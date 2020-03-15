package com.example.ddprojet.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.ddprojet.R;
import com.example.ddprojet.activity.CharacterEditionActivity;
import com.example.ddprojet.model.Alignment;
import com.example.ddprojet.model.Character;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class DescriptionFragment extends Fragment {

    private View v;
    private CharacterEditionActivity parent_activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.description_layout, container, false);

        this.parent_activity = (CharacterEditionActivity)this.getActivity();
        this.parent_activity.imageViewDescriptionFragmentAvatar = v.findViewById(R.id.imageViewAvatar);

        final Spinner ali1 = v.findViewById(R.id.al1);
        final Spinner ali2 = v.findViewById(R.id.al2);
        final TextView name = v.findViewById(R.id.namePerso);
        final TextView backGround = v.findViewById(R.id.backgound);
        final TextView characterTrait = v.findViewById(R.id.character_trait);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.array_lawfulChaoticAxis, android.R.layout.simple_spinner_item);
        ali1.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(v.getContext(),R.array.array_goodEvilAxis, android.R.layout.simple_spinner_item);
        ali2.setAdapter(adapter1);

        Button btn = v.findViewById(R.id.validation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation(ali1.getSelectedItem().toString(),ali2.getSelectedItem().toString(),name.getText().toString(),backGround.getText().toString(),characterTrait.getText().toString());
            }
        });

        Button buttonAvatar = v.findViewById(R.id.buttonAvatar);
        buttonAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMultiplePermissions();
                DescriptionFragment.this.showPictureDialog();
            }
        });


        return v;
    }

    public void validation(String spin1, String spin2, String name, String background, String trait){
        CharacterEditionActivity activity = (CharacterEditionActivity) getActivity();
        Character character = activity.getCharacter();

        character.setAlignment(new Alignment(spin1,spin2));
        character.setName(name);
        character.setPersonality_traits(trait);
        character.setBackground(background);

        activity.submit(v);

    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this.parent_activity);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        this.parent_activity.startActivityForResult(galleryIntent, 1);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        this.parent_activity.startActivityForResult(intent, 2);
    }


    private void  requestMultiplePermissions(){
        Dexter.withActivity(this.parent_activity)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(DescriptionFragment.this.parent_activity.getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(DescriptionFragment.this.parent_activity.getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

}
