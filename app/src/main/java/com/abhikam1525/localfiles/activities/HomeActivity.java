package com.abhikam1525.localfiles.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.abhikam1525.localfiles.R;
import com.abhikam1525.localfiles.activities.GalleryActivity;
import com.abhikam1525.localfiles.activities.MusicActivity;
import com.abhikam1525.localfiles.activities.PdfActivity;
import com.abhikam1525.localfiles.activities.VideosActivity;
import com.abhikam1525.localfiles.utils.Constraints;
import com.abhikam1525.localfiles.utils.PermissionManager;

public class HomeActivity extends AppCompatActivity {

    private final static int READ_PERMISSION = 10000;
    private ImageView imgMusic;
    private ImageView imgGallery;
    private ImageView imgPdf;
    private ImageView imgVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        permissionRelatedCode();

        init();
    }

    private void init(){
        imgMusic = findViewById(R.id.img_music_home);
        imgGallery = findViewById(R.id.img_gallery_home);
        imgPdf = findViewById(R.id.img_pdf_home);
        imgVideo = findViewById(R.id.img_video_home);

        setListeners();
    }

    private void setListeners(){
        imgMusic.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
            startActivity(intent);
        });

        imgVideo.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), VideosActivity.class);
            startActivity(intent);
        });

        imgPdf.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PdfActivity.class);
            startActivity(intent);
        });

        imgGallery.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
            startActivity(intent);
        });
    }

    private void permissionRelatedCode(){
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (PermissionManager.checkPermission(permission, this)) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
        else {
            // You can directly ask for the permission.
            PermissionManager.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case Constraints.READ_PERMISSION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }
}