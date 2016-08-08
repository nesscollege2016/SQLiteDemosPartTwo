package ness.tomerbu.edu.sqlitedemosparttwo.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ness.tomerbu.edu.sqlitedemosparttwo.R;
import ness.tomerbu.edu.sqlitedemosparttwo.db.SongDAO;
import ness.tomerbu.edu.sqlitedemosparttwo.models.Song;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends DialogFragment implements View.OnClickListener {
    EditText etArtist;
    EditText etDuration;
    EditText etTitle;
    EditText etID;
    EditText etImage;
    EditText etAlbum;
    Button btnSave;
    private String id;

    public static AddItemFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        AddItemFragment fragment = new AddItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add_item, container, false);
        //findViewByID
        etAlbum = (EditText) v.findViewById(R.id.etAlbum);
        etArtist = (EditText) v.findViewById(R.id.etArtist);
        etTitle = (EditText) v.findViewById(R.id.etTitle);
        etID = (EditText) v.findViewById(R.id.etID);
        etImage = (EditText) v.findViewById(R.id.etImage);
        etDuration = (EditText) v.findViewById(R.id.etDuration);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);


        Bundle arguments = getArguments();
        if (arguments!=null ){
            id = arguments.getString("id");
        }


        if (id!=null){
            Song s = SongDAO.getInstance(getContext()).getSong(id);
            etAlbum.setText(s.getAlbum());
            etArtist.setText(s.getArtist());
            etTitle.setText(s.getTitle());
            etImage.setText(s.getImage());
            etDuration.setText(s.getDuration());


        }
        return v;
    }

    @Override
    public void onClick(View view) {
        Song s = new Song(
                etAlbum.getText().toString(),
                etArtist.getText().toString(),
                etDuration.getText().toString(),
                etImage.getText().toString(),
                etTitle.getText().toString()
        );



        if (id == null)
            SongDAO.getInstance(getContext()).insert(s);
        else
            SongDAO.getInstance(getContext()).update(s, id);

        dismiss();

       if (listener!=null && id!=null)
           listener.onSongChanged(s);
        else if (listener!=null){
           listener.onSongAdded(s);
       }

    }

    OnSongAddedListener listener;

    public void setListener(OnSongAddedListener listener) {
        this.listener = listener;
    }


    public interface OnSongAddedListener{
        void onSongAdded(Song s);
        void onSongChanged(Song s);
    }
}
