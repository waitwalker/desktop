package com.example.desktop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desktop.R;
import com.example.desktop.entity.VideoEntity;

import java.util.List;


/**
 * @author: wei
 * @date: 2020-06-27
 **/
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntity> datas;

    public VideoAdapter(Context context, List<VideoEntity> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = datas.get(position);
        vh.tvTitle.setText(videoEntity.getTitle());
        vh.tvAuthor.setText(videoEntity.getName());
        vh.tvDz.setText(String.valueOf(videoEntity.getDzCount()));
        vh.tvComment.setText(String.valueOf(videoEntity.getCommentCount()));
        vh.tvCollect.setText(String.valueOf(videoEntity.getCollectCount()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvDz;
        private TextView tvComment;
        private TextView tvCollect;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.title);
            tvAuthor = view.findViewById(R.id.author);
            tvDz = view.findViewById(R.id.dz);
            tvComment = view.findViewById(R.id.comment);
            tvCollect = view.findViewById(R.id.collect);
        }
    }
}

