package com.abhikam1525.localfiles.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.abhikam1525.localfiles.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoViewActivity extends AppCompatActivity {

    private static final String TAG = "VideoViewActivity";
    private PlayerView playerView;
    private String videoUrl;
    private SimpleExoPlayer player;
    private ScaleGestureDetector scaleGestureDetector;
    private float factor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        
        videoUrl = getIntent().getStringExtra("videoUrl");

        init();
        playerSetUp();
    }

    private void init(){
        playerView = findViewById(R.id.playerView_video_view);

        scaleGestureDetector = new ScaleGestureDetector(this, new MySimpleOnScaleGestureListener(playerView));

    }

    private void playerSetUp(){
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
        player = new SimpleExoPlayer.Builder(getApplicationContext()).build();
        playerView.setPlayer(player);

        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        // Set the media item to be played.
        player.setMediaItem(mediaItem);
        // Prepare the player.
        player.prepare();
        // Start the playback.
        player.play();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
        //return super.onTouchEvent(event);
    }


    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    protected void onStop() {
        player.stop();
        player.release();
        super.onStop();
    }

    private class MySimpleOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        private PlayerView playerView;

        public MySimpleOnScaleGestureListener(PlayerView playerView) {
            super();
            this.playerView = playerView;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor() - 1;
            factor += scaleFactor;
            if(factor >= 1.0) {
                playerView.setScaleX(factor);
                playerView.setScaleY(factor);
                return true;
            }
            return false;
        }
    }
}