package ness.tomerbu.edu.sqlitedemosparttwo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by android on 01/08/2016.
 */
public class SongDBHelper extends SQLiteOpenHelper {

    public SongDBHelper(Context context) {
        super(context, "SongDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String songCreate =
                "CREATE TABLE " + SongContract.Song.TABLE_NAME + " ( " +
                        "    " + SongContract.Song.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "   " + SongContract.Song.COL_TITLE + " TEXT NOT NULL, " +
                        "   "  +SongContract.Song.COL_ARTIST+" TEXT, " +
                        "    "+ SongContract.Song.COL_IMAGE + " TEXT, " +
                        "    "+ SongContract.Song.COL_DURATION + " TEXT, " +
                        "   "+ SongContract.Song.COL_ALBUM + " TEXT " +
                        ");";
        db.execSQL(songCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropSong = "DROP TABLE " + SongContract.Song.TABLE_NAME;
        db.execSQL(dropSong);
        onCreate(db);
    }
}
