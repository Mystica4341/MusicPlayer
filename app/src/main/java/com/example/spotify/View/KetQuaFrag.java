package com.example.spotify.View;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.spotify.Control.CustomaAdapterMusic;
import com.example.spotify.Control.PlaylistControl;
import com.example.spotify.Control.SearchControl;
import com.example.spotify.Model.Music;
import com.example.spotify.Model.Play;
import com.example.spotify.R;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KetQuaFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KetQuaFrag extends Fragment {

    public static ArrayList<Music> lsMusicKetQua;

    ListView lvPlaylist;

    CustomaAdapterMusic customaAdapterMusic;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KetQuaFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static KetQuaFrag newInstance(String param1, String param2) {
        KetQuaFrag fragment = new KetQuaFrag();
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
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ketqua, container, false);
        addControl(view);
        addEvent();
        return view;
    }

    public void addControl(View view){
        lvPlaylist = (ListView) view.findViewById(R.id.lvPlaylist);
    }

    public void addEvent(){
        lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Play> arrayListPlay = new ArrayList<>();
                Play play = new Play();
                play.setId(lsMusicKetQua.get(position).getId());
                play.setName(lsMusicKetQua.get(position).getName());
                play.setArtist(lsMusicKetQua.get(position).getArtistName());
                play.setDuration(lsMusicKetQua.get(position).getDuration());
                play.setMusicURL(lsMusicKetQua.get(position).getMusicURL());
                PlayFrag playFrag = new PlayFrag();
                arrayListPlay.add(play);
                PlayFrag.arrayListPlay = arrayListPlay;
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, playFrag).commit();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                customaAdapterMusic = new CustomaAdapterMusic(requireContext(), R.layout.custom_music_item, lsMusicKetQua);
                lvPlaylist.setAdapter(customaAdapterMusic);
            }
        },500);

    }
}