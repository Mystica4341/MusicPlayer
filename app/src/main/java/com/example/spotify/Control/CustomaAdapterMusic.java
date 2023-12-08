package com.example.spotify.Control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.spotify.Model.Music;
import com.example.spotify.R;

import java.util.ArrayList;

public class CustomaAdapterMusic extends ArrayAdapter {
    Context context;

    int layoutItem;

    ArrayList<Music> lsMusic;
    public CustomaAdapterMusic(@NonNull Context context, int resource, ArrayList<Music> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutItem = resource;
        this.lsMusic = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Music m = lsMusic.get(position);
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(layoutItem, null);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(m.getName());

        TextView tvArist = (TextView) convertView.findViewById(R.id.tvArtist);
        tvArist.setText(m.getArtistName());
        String duration = "";
        if(m.getDuration() > 60){
            int thoiluong = m.getDuration();
            int phut = thoiluong/60;
            int giay = thoiluong%60;
            duration = phut + ":" + giay;
        }
        TextView tvDur = (TextView) convertView.findViewById(R.id.tvDur);
        tvDur.setText(duration);

        return convertView;
    }
}