package ness.tomerbu.edu.sqlitedemosparttwo.models;

import android.content.ContentValues;

import ness.tomerbu.edu.sqlitedemosparttwo.db.SongContract;

/**
 * Created by android on 01/08/2016.
 */
public class Song {
    private String title;
    private String album;
    private String duration;
    int id;
    private String artist;
    private String image;

    public Song(String album, String artist, String duration, int id, String image, String title) {
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.id = id;
        this.image = image;
        this.title = title;
    }

    public Song(String album, String artist, String duration, String image, String title) {
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.image = image;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Song{" +
                "album='" + album + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", id=" + id +
                ", artist='" + artist + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();

        values.put(SongContract.Song.COL_ALBUM, getAlbum());
        values.put(SongContract.Song.COL_ARTIST, getArtist());
        values.put(SongContract.Song.COL_DURATION, getDuration());
        values.put(SongContract.Song.COL_IMAGE, getImage());
        values.put(SongContract.Song.COL_TITLE, getTitle());
        values.put(SongContract.Song.COL_ID, getId());

        return values;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
