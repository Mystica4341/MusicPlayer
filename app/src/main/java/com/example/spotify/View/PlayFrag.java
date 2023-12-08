package com.example.spotify.View;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spotify.Model.Music;
import com.example.spotify.Model.Play;
import com.example.spotify.R;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFrag extends Fragment {

    TextView tvName, tvTenArtist, tvDurationEnd;

    Button btnTim, btnPause_Play, btnV_play;
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
                writeDSNV();
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
        }
    }

    public void writeDSNV()
    {
        try {
            OutputStream outputStream= getContext().openFileOutput("yeuthich.txt", Context.MODE_APPEND);
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}