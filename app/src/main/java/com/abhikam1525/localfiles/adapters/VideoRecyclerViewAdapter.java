package com.abhikam1525.localfiles.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abhikam1525.localfiles.R;
import com.abhikam1525.localfiles.activities.VideoViewActivity;
import com.abhikam1525.localfiles.models.Video;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "VideoRecyclerViewAdapter";
    public List<Video> videoList;
    private  Context context;

    public VideoRecyclerViewAdapter(List<Video> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_video, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videoList.get(position);

        Glide.with(context)
                .load(video.data)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.imgViewThumbNail);

        holder.imgViewThumbNail.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoViewActivity.class);
            intent.putExtra("videoUrl", video.data);
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgViewThumbNail;
//        private final CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewThumbNail = itemView.findViewById(R.id.img_view_thumbnail_video);
//            cardView = itemView.findViewById(R.id.cardview_video);
        }
    }
}

