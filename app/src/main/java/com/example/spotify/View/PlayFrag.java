package com.example.spotify.View;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.spotify.Model.Play;
import com.example.spotify.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFrag extends Fragment {
    String outputWrite;
    TextView tvName, tvTenArtist, tvDurationEnd, tvDurationStart;
    SeekBar seekBarPlay;
    Button btnTim, btnPause_Play, btnV_play;
    ImageView imgMusicPlay;
    MediaPlayer mediaPlayer = new MediaPlayer();
    public static ArrayList<Play> arrayListPlay;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayFrag newInstance(String param1, String param2) {
        PlayFrag fragment = new PlayFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_play, container, false);
        addControl(view);
        addEvent();
        return view;

    }

    public void addEvent(){
        initData();
        btnPause_Play.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                Drawable[] compoundDraw = btnPause_Play.getCompoundDrawables();
                Bitmap leftCompound = ((BitmapDrawable)compoundDraw[0]).getBitmap();
                Bitmap checkImg = ((BitmapDrawable) requireActivity().getDrawable(R.drawable.play)).getBitmap();
                if(leftCompound == checkImg){
                    btnPause_Play.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pause,0,0,0);
                    mediaPlayer.start();
                }
                else{
                    btnPause_Play.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play,0,0,0);
                    mediaPlayer.pause();
                }

            }
        });
        seekBarPlay.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBarPlay.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,900);
        seekBarPlay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int giaytruoc = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
                int phut = i/970/60;
                int giay = i/970%60;
                if (giay < 10)
                    tvDurationStart.setText(phut + ":0" + giay);
                else
                    tvDurationStart.setText(phut + ":" + giay);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                seekBarPlay.setProgress(0);
                btnPause_Play.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play,0,0,0);
            }
        });
        btnV_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFrag homeFrag = new HomeFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, homeFrag).commit();
            }
        });
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLovedSong();
            }
        });
    }
    public void addControl(View view){
        tvName = (TextView) view.findViewById(R.id.tvSongName);
        tvTenArtist = (TextView) view.findViewById(R.id.tvArtistName);
        tvDurationEnd = (TextView) view.findViewById(R.id.tvDurationEnd);
        btnTim = (Button) view.findViewById(R.id.btnTim_play);
        btnPause_Play = (Button)view.findViewById(R.id.btnPause_play);
        btnV_play = (Button) view.findViewById(R.id.btnV_play);
        imgMusicPlay =(ImageView) view.findViewById(R.id.imgMusicPlay);
        seekBarPlay = (SeekBar)view.findViewById(R.id.seekBarPlay);
        tvDurationStart = (TextView)view.findViewById(R.id.tvDurationStart);
    }
    public void initData(){
        for(Play play: arrayListPlay){
            tvName.setText(play.getName());
            String duration = "";
            int phut = play.getDuration()/60;
            int giay = play.getDuration()%60;
            if (giay < 10) {
                duration = phut + ":0" + giay;
            }else duration = phut + ":" + giay;
            tvDurationEnd.setText(duration);
            tvTenArtist.setText(play.getArtist());
            mediaPlayer = MediaPlayer.create(requireContext(),play.getMusicURL());
            Picasso.get().load(play.getImage()).resize(340,340).into(imgMusicPlay);
            outputWrite = play.getId() + " --- " + play.getName() + " --- " + play.getArtist() + " --- " + play.getDuration() + " --- " + play.getMusicURL() + "\n";
        }
    }

    public void saveLovedSong()
    {
        try {
            OutputStream outputStream= getContext().openFileOutput("yeuthich.txt", Context.MODE_APPEND);
            outputStream.write(outputWrite.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}