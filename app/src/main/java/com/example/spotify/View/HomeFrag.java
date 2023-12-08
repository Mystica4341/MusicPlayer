package com.example.spotify.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.Control.SearchControl;
import com.example.spotify.Model.Artist;
import com.example.spotify.Model.Music;
import com.example.spotify.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFrag extends Fragment {

    ImageView imglmht_home, imgRV_home, imgVpop_home, imgHipHop_home, imgObito_home, imgMCK_home, imgMaroon_home, imgAlan_home;

    String url = "https://api.deezer.com/search?q=";


    ArrayList<Music> lsMusic = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFrag newInstance(String param1, String param2) {
        HomeFrag fragment = new HomeFrag();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addControl(view);
        addEvent();
        return view;
    }

    public void addControl(View view){
        imglmht_home = view.findViewById(R.id.imglmht_home);
        imgRV_home = view.findViewById(R.id.imgRV_home);
        imgVpop_home = view.findViewById(R.id.imgVpop_home);
        imgHipHop_home = view.findViewById(R.id.imgHipHop_home);
        imgObito_home = view.findViewById(R.id.imgObito_home);
        imgMCK_home = view.findViewById(R.id.imgMCK_home);
        imgMaroon_home = view.findViewById(R.id.imgMaroon5_home);
        imgAlan_home = view.findViewById(R.id.imgAlanWalker_home);
    }

    public void addEvent(){
        imglmht_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlLOL = url + "League of Legend";
                connectAPI(urlLOL);
                lsMusic = new ArrayList<>();
                KetQuaFrag ketQuaFrag = new KetQuaFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, ketQuaFrag).commit();
            }
        });
        imgRV_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlRV = url + "Rap Viet";
                connectAPI(urlRV);
                lsMusic = new ArrayList<>();
                KetQuaFrag ketQuaFrag = new KetQuaFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, ketQuaFrag).commit();
            }
        });
        imgVpop_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlVpop = url + "Anh Phan";
                connectAPI(urlVpop);
                lsMusic = new ArrayList<>();
                KetQuaFrag ketQuaFrag = new KetQuaFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, ketQuaFrag).commit();
            }
        });
        imgHipHop_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlHipHop = url + "Low G";
                connectAPI(urlHipHop);
                lsMusic = new ArrayList<>();
                KetQuaFrag ketQuaFrag = new KetQuaFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, ketQuaFrag).commit();
            }
        });
        imgObito_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlObito = url + "Obito";
                connectAPI(urlObito);
                lsMusic = new ArrayList<>();
                KetQuaFrag ketQuaFrag = new KetQuaFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, ketQuaFrag).commit();
            }
        });
        imgMCK_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlMCK = url + "RPT MCK";
                connectAPI(urlMCK);
                lsMusic = new ArrayList<>();
                KetQuaFrag ketQuaFrag = new KetQuaFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, ketQuaFrag).commit();
            }
        });
        imgMaroon_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlMaroon = url + "Maroon 5";
                connectAPI(urlMaroon);
                lsMusic = new ArrayList<>();
                KetQuaFrag ketQuaFrag = new KetQuaFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, ketQuaFrag).commit();
            }
        });
        imgAlan_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAlan = url + "Alan Walker";
                connectAPI(urlAlan);
                lsMusic = new ArrayList<>();
                KetQuaFrag ketQuaFrag = new KetQuaFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.FrameFrag, ketQuaFrag).commit();
            }
        });
    }

    public void connectAPI(String url) {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    parseData(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        queue.add(stringRequest);
    }

    @SuppressLint("SuspiciousIndentation")
    public void parseData(String string) throws JSONException {
        JSONObject object = new JSONObject(string);
        for (int i = 0; i < object.length(); i++) {
            if (object.has("data")) {
                JSONArray jsonArray = object.getJSONArray("data");
                for (int y = 0; y < jsonArray.length(); y++) {
                    Music m = new Music();
                    Artist a = new Artist();
                    JSONObject subObj1 = jsonArray.getJSONObject(y);
                    m.setId(subObj1.getString("id"));
                    m.setName(subObj1.getString("title"));
                    m.setDuration(subObj1.getInt("duration"));
                    m.setMusicURL(Uri.parse(subObj1.getString("preview")));
                    if (subObj1.has("artist")) {
                        JSONObject subObj2 = subObj1.getJSONObject("artist");
                        m.setArtistName(subObj2.getString("name"));
                        a.setId(subObj2.getString("id"));
                        a.setName(subObj2.getString("name"));
                    }
                    m.setType(subObj1.getString("type"));
                    lsMusic.add(m);
                }
            }
        }
        KetQuaFrag.lsMusicKetQua = lsMusic;
    }
}