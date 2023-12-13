package com.example.spotify.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.example.spotify.Model.Account;
import com.example.spotify.Model.LovedSong;

import java.util.ArrayList;

public class LovedSongControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectandroid";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.example.spotify/database/projectandroid.db";
    public static final String TABLE_NAME = "LovedSong";
    public static String IDSONG = "id";
    private static final String TENSONG = "tenSong";
    private static final String SONGARTIST = "nghesi";
    private static final String DURATION = "duration";
    private static final String MUSICURL = "musicurl";
    private static final String IMAGESONG = "imgSong";
    public LovedSongControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + IDSONG + " TEXT PRIMARY KEY NOT NULL UNIQUE," + TENSONG + " TEXT NOT NULL," + SONGARTIST + " TEXT NOT NULL, "+DURATION+" INTEGER NOT NULL, "+MUSICURL+" TEXT NOT NULL, "+IMAGESONG+" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertData(String id, String tenSong,int duration, String ngheSi, Uri musicURL, Uri imgSong) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDSONG, id);
        value.put(TENSONG, tenSong);
        value.put(SONGARTIST, ngheSi);
        value.put(DURATION,duration);
        value.put(MUSICURL, String.valueOf(musicURL));
        value.put(IMAGESONG, String.valueOf(imgSong));
        db.insert(TABLE_NAME, null, value);
        db.close();
    }
    public ArrayList<LovedSong> loadData(){
        ArrayList<LovedSong> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            LovedSong lS = new LovedSong();
            lS.setId(cursor.getString(0));
            lS.setName(cursor.getString(1));
            lS.setArtist(cursor.getString(2));
            lS.setDuration(cursor.getInt(3));
            lS.setMusicURL(Uri.parse(cursor.getString(4)));
            lS.setImage(Uri.parse(cursor.getString(5)));
            result.add(lS);
        } while (cursor.moveToNext());
        return result;
    }
    public void deleteData(String id) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, IDSONG + " =?",
                new String[]{id});
        db.close();
    }
}
