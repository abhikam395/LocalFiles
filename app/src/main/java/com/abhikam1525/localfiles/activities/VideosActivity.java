package com.abhikam1525.localfiles.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhikam1525.localfiles.R;
import com.abhikam1525.localfiles.adapters.VideoRecyclerViewAdapter;
import com.abhikam1525.localfiles.models.Video;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VideosActivity extends AppCompatActivity {

    private static final String TAG = "VideosActivity";
    private RecyclerView rvVideos;
    private VideoRecyclerViewAdapter adapter;
    private List<Video> videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        init();
    }

    private void init(){
        videoList = getVideos();
        rvVideos = findViewById(R.id.rv_videos);
        adapter = new VideoRecyclerViewAdapter(videoList, getApplicationContext());

        setRecyclerViewManager();
    }

    private void setRecyclerViewManager(){
        rvVideos.setLayoutManager(new GridLayoutManager(this, 3));
        rvVideos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<Video> getVideos() {
        HashSet<Video> videoItemHashSet = new HashSet<>();
        String[] projection = {
                    MediaStore.Video.VideoColumns.DATA ,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.SIZE };
        Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection,
                    null, null, null);
        try {
            cursor.moveToFirst();
            do{
                String data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                String size = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                videoItemHashSet.add(new Video(data, size, name));
            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Video> downloadedList = new ArrayList<>(videoItemHashSet);
        return downloadedList;
    }
}