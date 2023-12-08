package com.example.spotify.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spotify.Model.Music;
import com.example.spotify.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFrag extends Fragment {

    TextView tvName, tvTenArtist, tvDurationEnd;

    Button btnTim;

    String id, name, artist;

    int duration;

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
        addEvent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_play, container, false);
        tvName = (TextView) view.findViewById(R.id.tvTenBaiHat);
        tvTenArtist = (TextView) view.findViewById(R.id.tvArtistName);
        tvDurationEnd = (TextView) view.findViewById(R.id.tvDurationEnd);
        btnTim = (Button) view.findViewById(R.id.btnTim_play);

        return view;

    }

    public void addEvent(){
        FragmentManager fm = getChildFragmentManager();
        fm.setFragmentResultListener("keyMain", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Music m = new Music();
                id = result.getString("idSong");
                name = result.getString("nameSong");
                artist = result.getString("artist");
                duration = Integer.parseInt(result.getString("dur"));
                tvName.setText(name);
                tvDurationEnd.setText(duration);
                tvTenArtist.setText(artist);
            }
        });
    }
}