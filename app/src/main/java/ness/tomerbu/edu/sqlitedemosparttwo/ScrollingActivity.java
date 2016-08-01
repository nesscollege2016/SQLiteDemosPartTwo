package ness.tomerbu.edu.sqlitedemosparttwo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    EditText etSongName;
    EditText etArtist;
    EditText etImage;
    EditText etDuration;
    EditText etID;
    EditText etAlbum;
    private boolean isDataValid;
    private ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etSongName = (EditText) findViewById(R.id.etTitle);
        etArtist = (EditText) findViewById(R.id.etArtist);
        etImage = (EditText) findViewById(R.id.etImage);
        etDuration = (EditText) findViewById(R.id.etDuration);
        etID = (EditText) findViewById(R.id.etID);
        etAlbum = (EditText) findViewById(R.id.etAlbum);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insert(View view) {
        if (!isDataValid())
            return;
        SQLiteDatabase db = getDB();
        ContentValues values = getContentValues();

        //values.put(SongContract.Song.COL_ID, getID());


        long insertedID = db.insert(
                SongContract.Song.TABLE_NAME,
                null,
                values
        );

        Toast.makeText(ScrollingActivity.this, "" + insertedID,
                Toast.LENGTH_SHORT).show();
        clearEditTexts();

    }

    private void clearEditTexts() {
        etID.setText("");
        etSongName.setText("");
        etImage.setText("");
        etDuration.setText("");
        etArtist.setText("");
        etAlbum.setText("");
    }

    public void delete(View view) {
        SQLiteDatabase db = getDB();

        int deleteCount = db.delete(SongContract.Song.TABLE_NAME,
                SongContract.Song.COL_TITLE + " = ?",
                new String[]{"Hello"}
        );

        Toast.makeText(ScrollingActivity.this, "" + deleteCount, Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        SQLiteDatabase db = getDB();
        ContentValues values = getContentValues();

        db.update(SongContract.Song.TABLE_NAME,
                values,
                "Title = ?", new String[]{"Holla"}
        );
    }

    public void read(View view) {

        SQLiteDatabase db = getDB();


        String[] columns = {SongContract.Song.COL_TITLE, SongContract.Song.COL_ARTIST};

         Cursor cursor = db.query(SongContract.Song.TABLE_NAME,
                null, null, null, null, null, null);

/*
        /*Cursor cursor = db.query(SongContract.Song.TABLE_NAME,
                columns, "Title <> ?", new String[]{"Hello"}, null, null, null);*/

/*
        Cursor cursor = db.query(SongContract.Song.TABLE_NAME,
                columns, null, null, "Title", "COUNT(Title) > 1", "_ID DESC");
*/

        cursor.moveToFirst();

        do{
          //  int id = cursor.getInt(cursor.getColumnIndex(SongContract.Song.COL_ID));
           // String album = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_ALBUM));
            String title = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_TITLE));
            String artist = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_ARTIST));
           // String image = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_IMAGE));
          //  String duration = cursor.getString(cursor.getColumnIndex(SongContract.Song.COL_DURATION));
           // String text = String.format("Title: %s \nAlbum: %s\nArtist: %s\nImage: %s Duration: %s", title, album, artist, image, duration);

            Toast.makeText(ScrollingActivity.this, String.format("%s %s", title, artist), Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext());

    }

    public String getAlbum() {
        return etAlbum.getText().toString();
    }

    public String getArtist() {
        return etArtist.getText().toString();
    }

    public String getDuration() {
        return etDuration.getText().toString();
    }

    public String getImage() {
        return etImage.getText().toString();
    }


    public String getSongTitle() {
        return etSongName.getText().toString();
    }

    public String getID() {
        return etID.getText().toString();
    }

    public boolean isDataValid() {
        boolean isDataValid = getSongTitle().length() >= 2;
        if (!isDataValid)
            etSongName.setError("Must be at least 2 characters");
        return isDataValid;
    }

    public SQLiteDatabase getDB() {
        SongDBHelper helper = new SongDBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        return db;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(SongContract.Song.COL_ALBUM, getAlbum());
        values.put(SongContract.Song.COL_ARTIST, getArtist());
        values.put(SongContract.Song.COL_DURATION, getDuration());
        values.put(SongContract.Song.COL_IMAGE, getImage());
        values.put(SongContract.Song.COL_TITLE, getSongTitle());
        return values;
    }
}
