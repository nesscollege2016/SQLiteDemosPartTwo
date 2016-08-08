package ness.tomerbu.edu.sqlitedemosparttwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ness.tomerbu.edu.sqlitedemosparttwo.adapters.SongRecyclerAdapter;
import ness.tomerbu.edu.sqlitedemosparttwo.fragments.AddItemFragment;
import ness.tomerbu.edu.sqlitedemosparttwo.models.Song;

public class ScrollingActivity extends AppCompatActivity {

    private SongRecyclerAdapter adapter;
    private RecyclerView rvSongs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvSongs = (RecyclerView) findViewById(R.id.songRecycler);
        adapter = new SongRecyclerAdapter(this, getSupportFragmentManager());
        rvSongs.setLayoutManager(new LinearLayoutManager(this));
        rvSongs.setAdapter(adapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                adapter.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        itemTouchHelper.attachToRecyclerView(rvSongs);
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

    public void addSong(View view) {
        AddItemFragment fragment = new AddItemFragment();

        fragment.setListener(new AddItemFragment.OnSongAddedListener() {
            @Override
            public void onSongAdded(Song s) {
                adapter.addItem(s, rvSongs);

            }

            @Override
            public void onSongChanged(Song s) {
                //
            }
        });
        fragment.show(getSupportFragmentManager(), "Dialog");

    }
}
