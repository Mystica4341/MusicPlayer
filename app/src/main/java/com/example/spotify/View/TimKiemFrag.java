package com.example.spotify.View;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.spotify.Control.CustomaAdapterMusic;
import com.example.spotify.Control.MusicControl;
import com.example.spotify.Control.SearchControl;
import com.example.spotify.Model.Music;
import com.example.spotify.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimKiemFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimKiemFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String searchUrl = "https://api.deezer.com/search?q=";
    EditText edtTim;
    ArrayList<Music> lsMusic = new ArrayList<>();
    RecyclerView SearchView;
    ListView lvMusic;

    CustomaAdapterMusic customaAdapterMusic;

    LinearLayout lnImage;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TimKiemFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimKiemFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TimKiemFrag newInstance(String param1, String param2) {
        TimKiemFrag fragment = new TimKiemFrag();
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
        View view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        edtTim = (EditText) view.findViewById(R.id.edtTimkiem);
        lnImage = (LinearLayout) view.findViewById(R.id.linearImage);
        lvMusic = (ListView) view.findViewById(R.id.lvMusic);
        addEvent();
        return view;
    }

    public void addEvent(){
        edtTim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence keyword, int start, int before, int count) {
                ViewGroup.LayoutParams lnParam = lnImage.getLayoutParams();
                lnParam.height = 0;
                lnImage.setLayoutParams(lnParam);
                String url = searchUrl + keyword.toString();
                SearchControl control = new SearchControl();
                lsMusic = control.getData(getContext(),url);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customaAdapterMusic = new CustomaAdapterMusic(requireContext(), R.layout.custom_music_item, lsMusic);
                        lvMusic.setAdapter(customaAdapterMusic);
                        lsMusic = new ArrayList<>();
                    }
                },100);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}