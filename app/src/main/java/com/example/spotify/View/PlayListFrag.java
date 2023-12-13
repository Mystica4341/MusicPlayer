package com.example.spotify.View;

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

import com.example.spotify.Control.CustomAdapterPlayList;
import com.example.spotify.Control.CustomaAdapterMusic;
import com.example.spotify.Control.PlaylistControl;
import com.example.spotify.Model.Play;
import com.example.spotify.Model.PlayList;
import com.example.spotify.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayListFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayListFrag extends Fragment {
    ListView lvPlaylist;
    PlaylistControl playlistControl;
    ArrayList<PlayList> lstPlayList = new ArrayList<>();
    CustomAdapterPlayList customAdapterPlayList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayListFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayListFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayListFrag newInstance(String param1, String param2) {
        PlayListFrag fragment = new PlayListFrag();
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
        View view = inflater.inflate(R.layout.fragment_play_list, container, false);
        addControls(view);
        addEvent();
        customAdapterPlayList = new CustomAdapterPlayList(requireContext(), R.layout.custom_playlist_item, lstPlayList);
        lvPlaylist.setAdapter(customAdapterPlayList);
        return view;
    }
    public void addControls(View view){
        lvPlaylist = (ListView) view.findViewById(R.id.lvPlaylist);
    }
    public void addEvent(){
        initData();
        lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Play> arrayListPlay = new ArrayList<>();
                Play play = new Play();
                play.setId(lstPlayList.get(position).getId());
                play.setName(lstPlayList.get(position).getName());
                play.setArtist(lstPlayList.get(position).getArtist());
                play.setDuration(lstPlayList.get(position).getDuration());
                play.setMusicURL(lstPlayList.get(position).getMusicURL());
                play.setImage(lstPlayList.get(position).getImageMusic());
                PlayFrag playFrag = new PlayFrag();
                arrayListPlay.add(play);
                PlayFrag.arrayListPlay = arrayListPlay;
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, playFrag).commit();
            }
        });
    }
    public void initData(){
        playlistControl = new PlaylistControl(requireContext(),PlaylistControl.DATABASE_NAME,null,1);
        try {
            lstPlayList = playlistControl.loadData();
        }catch (IndexOutOfBoundsException e){

        }

    }
}