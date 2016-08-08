package ness.tomerbu.edu.sqlitedemosparttwo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ness.tomerbu.edu.sqlitedemosparttwo.R;
import ness.tomerbu.edu.sqlitedemosparttwo.db.SongDAO;
import ness.tomerbu.edu.sqlitedemosparttwo.models.Song;

/**
 * Created by android on 08/08/2016.
 */
public class SongRecyclerAdapter extends RecyclerView.Adapter<SongRecyclerAdapter.SongViewHolder> {


    private final Context context;
    private final LayoutInflater inflater;
    private final ArrayList<Song> songs;

    public SongRecyclerAdapter(Context context){
        this.context = context;
        this.inflater = LayoutInflater.from(context);

        songs = SongDAO.getInstance(context).read();

    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(v);
    }

    @Override
    public void onBindViewHolder(
            SongViewHolder holder,
            int position) {
        Song s = songs.get(position);
        holder.tvSongTitle.setText(s.getTitle());
        holder.tvSongDuration.setText(s.getDuration());
        holder.tvArtist.setText(s.getArtist());

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    //findViewByID
    class SongViewHolder extends RecyclerView.ViewHolder{
        TextView tvSongTitle;
        TextView tvSongDuration;
        TextView tvArtist;
        ImageView ivSong;

        public SongViewHolder(View v) {
            super(v);
            tvArtist = (TextView) v.findViewById(R.id.tvArtist);
            tvSongTitle = (TextView) v.findViewById(R.id.tvSongTitle);
            tvSongDuration = (TextView) v.findViewById(R.id.tvDuration);
            ivSong = (ImageView) v.findViewById(R.id.ivSong);
        }
    }
}
