package com.example.spotify.Control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.spotify.Model.PlayList;
import com.example.spotify.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterPlayList extends ArrayAdapter {
    ArrayList<PlayList> lstPlayList;
    Context context;

    int layoutItem;
    public CustomAdapterPlayList(@NonNull Context context, int resource, ArrayList<PlayList> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutItem = resource;
        this.lstPlayList = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlayList playList = lstPlayList.get(position);
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(layoutItem, null);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNamePL);
        tvName.setText(playList.getName());

        TextView tvArist = (TextView) convertView.findViewById(R.id.tvArtistPL);
        tvArist.setText(playList.getArtist());
        String duration = "";
        int thoiluong = playList.getDuration();
        int phut = thoiluong/60;
        int giay = thoiluong%60;
        if (giay < 10)
            duration = phut + ":0" + giay;
        else duration = phut + ":" + giay;
        TextView tvDur = (TextView) convertView.findViewById(R.id.tvDurPL);
        tvDur.setText(duration);
        ImageView imgMusic = (ImageView)convertView.findViewById(R.id.imgMusicPL);
        Picasso.get().load(playList.getImageMusic()).resize(64,64).into(imgMusic);
        return convertView;
    }
}
