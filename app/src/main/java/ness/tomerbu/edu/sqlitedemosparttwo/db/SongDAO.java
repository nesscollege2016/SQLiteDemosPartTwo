package ness.tomerbu.edu.sqlitedemosparttwo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ness.tomerbu.edu.sqlitedemosparttwo.models.Song;

/**
 *
 * 1) private constructor-- available to the class only:
 * 2) define a sharedInstance as a field *(Same type as the class)
 * 3) define a static method: getInstance: init the sharedInstance if needed and returns the same shared instance
 */
public class SongDAO {

    private SongDAO(Context context) {
        this.context = context;
    }

    private Context context;
    private static SongDAO sharedInstance = null;


    //Singleton: define a single instance for the class:
    public static SongDAO getInstance(Context context){
        if (sharedInstance==null){
            sharedInstance = new SongDAO(context);
        }
        return sharedInstance;
    }


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

        ContentValues values = getContentValues(song);

       return insert(values);

    }

    private ContentValues getContentValues(Song song){
        ContentValues values = new ContentValues();
        values.put(SongContract.Song.COL_ALBUM, song.getAlbum());
        values.put(SongContract.Song.COL_ARTIST, song.getArtist());
        values.put(SongContract.Song.COL_DURATION, song.getDuration());
        values.put(SongContract.Song.COL_TITLE, song.getTitle());
        values.put(SongContract.Song.COL_IMAGE, song.getImage());

        return values;
    }

    public SQLiteDatabase getDB() {
        SongDBHelper helper = new SongDBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        return db;
    }

    public int update(ContentValues values, String id) {
        SQLiteDatabase db = getDB();


        int updateCount = db.update(SongContract.Song.TABLE_NAME,
                values,
                "_ID = ?", new String[]{ id}
        );
        return updateCount;
    }


    public int delete(int id) {
        SQLiteDatabase db = getDB();

        int deleteCount = db.delete(
                SongContract.Song.TABLE_NAME,
                " _ID = ? ",
                new String[]{id + ""}
        );
        return deleteCount;
    }


    public int delete(String songTitle) {
        SQLiteDatabase db = getDB();

        int deleteCount = db.delete(SongContract.Song.TABLE_NAME,
                SongContract.Song.COL_TITLE + " = ? ",
                new String[]{songTitle + ""});
        return deleteCount;

    }

    public ArrayList<Song> read() {
        SQLiteDatabase db = getDB();
        Cursor cursor = db.query(SongContract.Song.TABLE_NAME, null, null, null, null, null, null);
        return getSongs(cursor);

    }

    public static ArrayList<Song> getSongs(Cursor cursor){

        ArrayList<Song> songs = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                String title = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_ARTIST));
                String album = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_ALBUM));
                String duration = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_DURATION));
                String image = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_IMAGE));
                Integer id = cursor.getInt(cursor.getColumnIndex(SongContract.Song.COL_ID));
                Song s = new Song(album, artist, duration, id, image, title);
                songs.add(s);
            }while (cursor.moveToNext());
        }

        return songs;
    }

    public void update(Song s, String id) {
        ContentValues contentValues = getContentValues(s);
        update(contentValues, id);
    }

    public Song getSong(String id) {
        SQLiteDatabase db = getDB();
        Cursor cursor = db.query(SongContract.Song.TABLE_NAME, null, "_ID = ?", new String[]{id}, null, null, null);
        cursor.moveToFirst();


        String title = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_TITLE));
        String artist = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_ARTIST));
        String album = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_ALBUM));
        String duration = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_DURATION));
        String image = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_IMAGE));

        Song s = new Song(album, artist, duration, image, title);

        return s;

    }
}
