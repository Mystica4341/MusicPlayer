package com.example.spotify.View;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.spotify.Model.Music;
import com.example.spotify.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThuVienFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThuVienFrag extends Fragment {
    ArrayList<Music> lstMusic = new ArrayList<>();
    LinearLayout lnFavSong;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThuVienFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThuVienFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ThuVienFrag newInstance(String param1, String param2) {
        ThuVienFrag fragment = new ThuVienFrag();
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
        View view = inflater.inflate(R.layout.fragment_thu_vien, container, false);
        addControl(view);
        addEvent();
        return view;
    }
    public void readLovedSong(){
        try {
            InputStream inputStream= getContext().openFileInput("yeuthich.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String inputText = new String(buffer);
            String[] inputText1 = inputText.split("\n");
            for(String str: inputText1){
                String[] inputText2 = str.split(" --- ");
                Music m = new Music();
                m.setName(inputText2[1]);
                m.setArtistName(inputText2[2]);
                m.setDuration(Integer.parseInt(inputText2[3]));
                m.setId(inputText2[0]);
                m.setMusicURL(Uri.parse(inputText2[4]));
                lstMusic.add(m);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addControl(View view){
        lnFavSong = (LinearLayout)view.findViewById(R.id.lnFavSong);
    }
    public void addEvent(){
        readLovedSong();
        lnFavSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KetQuaFrag kqFrag = new KetQuaFrag();
                KetQuaFrag.lsMusicKetQua = lstMusic;
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, kqFrag).commit();
            }
        });
    }
}