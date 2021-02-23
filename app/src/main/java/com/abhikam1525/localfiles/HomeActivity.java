package com.abhikam1525.localfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.abhikam1525.localfiles.activities.GalleryActivity;
import com.abhikam1525.localfiles.activities.MusicActivity;
import com.abhikam1525.localfiles.activities.PdfActivity;
import com.abhikam1525.localfiles.activities.VideosActivity;

public class HomeActivity extends AppCompatActivity {

    private ImageView imgMusic;
    private ImageView imgGallery;
    private ImageView imgPdf;
    private ImageView imgVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
}