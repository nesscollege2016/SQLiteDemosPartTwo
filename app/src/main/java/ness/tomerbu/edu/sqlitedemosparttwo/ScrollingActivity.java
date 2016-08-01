package ness.tomerbu.edu.sqlitedemosparttwo;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ScrollingActivity extends AppCompatActivity {

    EditText etSongName;
    EditText etArtist;
    EditText etImage;
    EditText etDuration;
    EditText etID;
    EditText etAlbum;

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
        SongDBHelper helper = new SongDBHelper(this);
        ContentValues values = new ContentValues();
        values.put(SongContract.Song.COL_ALBUM, getAlbum());
        values.put(SongContract.Song.COL_ARTIST, getArtist());
        values.put(SongContract.Song.COL_DURATION, getDuration());
        values.put(SongContract.Song.COL_IMAGE, getImage());
        values.put(SongContract.Song.COL_TITLE, getSongTitle());
        //values.put(SongContract.Song.COL_ID, getID());



        helper.getWritableDatabase().insert(
                SongContract.Song.TABLE_NAME,
                null,
                values
        );

    }

    public void delete(View view) {
    }

    public void update(View view) {
    }

    public void read(View view) {
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
}
