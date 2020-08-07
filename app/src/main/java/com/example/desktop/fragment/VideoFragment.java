package com.example.desktop.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desktop.R;
import com.example.desktop.adapter.VideoAdapter;
import com.example.desktop.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    private String title;

    public VideoFragment() {
    }

    public static VideoFragment newInstance(String title) {
        VideoFragment fragment = new VideoFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<VideoEntity> datas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            VideoEntity ve = new VideoEntity();
            ve.setTitle("韭菜盒子新做法，不发面不烫面");
            ve.setName("大胃王");
            ve.setDzCount(i * 2);
            ve.setCollectCount(i * 4);
            ve.setCommentCount(i * 6);
            datas.add(ve);
        }
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), datas);
        recyclerView.setAdapter(videoAdapter);
        return v;
    }
}