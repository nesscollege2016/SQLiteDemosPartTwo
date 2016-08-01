package ness.tomerbu.edu.sqlitedemosparttwo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ness.tomerbu.edu.sqlitedemosparttwo.models.Song;

/**
 * Created by android on 01/08/2016.
 */
public class SongDAO {
    public SongDAO(Context context) {
        this.context = context;
    }

    private Context context;



    public long insert(ContentValues values) {
        SQLiteDatabase db = getDB();

        long insertedID = db.insert(
                SongContract.Song.TABLE_NAME,
                null,
                values
        );

        return insertedID;

    }


    public long insert(Song song) {

        ContentValues values = song.getContentValues();
        SQLiteDatabase db = getDB();

        long insertedID = db.insert(
                SongContract.Song.TABLE_NAME,
                null,
                values
        );

        return insertedID;

    }


    public SQLiteDatabase getDB() {
        SongDBHelper helper = new SongDBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        return db;
    }

    public int update(ContentValues values, int id) {
        SQLiteDatabase db = getDB();


        int updateCount = db.update(SongContract.Song.TABLE_NAME,
                values,
                "_ID = ?", new String[]{"" + id}
        );
        return updateCount;
    }


    public int delete(int id) {
        SQLiteDatabase db = getDB();

        int deleteCount = db.delete(SongContract.Song.TABLE_NAME,
                SongContract.Song.COL_ID + " = ?",
                new String[]{"" + id}
        );
        return deleteCount;

     }

    public ArrayList<Song> read() {

        SQLiteDatabase db = getDB();

        Cursor cursor = db.query(SongContract.Song.TABLE_NAME,
                null, null, null, null, null, null);

        return getSongs(cursor);

    }

    public static ArrayList<Song> getSongs(Cursor cursor){

        ArrayList<Song> songs = new ArrayList<>();
        cursor.moveToFirst();

        do{
            int id = cursor.getInt(cursor.getColumnIndex(SongContract.Song.COL_ID));
            String album = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_ALBUM));
            String title = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_TITLE));
            String artist = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_ARTIST));
            String image = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_IMAGE));
            String duration = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_DURATION));
            Song s = new Song(album, artist, duration, id, image, title);
            songs.add(s);
        }
        while (cursor.moveToNext());

        return songs;
    }

}
