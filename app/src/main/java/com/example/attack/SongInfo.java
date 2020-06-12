package com.example.attack;

/**
 * Created by pawankumar on 30/05/17.
 */

public class SongInfo {
    private String Songname;
    private String Artistname;
    private String SongUrl;
    private String Album;
    private String duration;
    public SongInfo() {
    }

    public SongInfo(String songname, String artistname, String songUrl) {
        Songname = songname;
        Artistname = artistname;
        SongUrl = songUrl;
    }

    public SongInfo(String songname, String artistname, String songUrl, String album, String duration) {
        Songname = songname;
        Artistname = artistname;
        SongUrl = songUrl;
        Album = album;
        this.duration = duration;
    }

    public SongInfo(String songname, String artistname, String songUrl, String album) {
        Songname = songname;
        Artistname = artistname;
        SongUrl = songUrl;
        Album = album;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSongname() {
        return Songname;
    }

    public void setSongname(String songname) {
        Songname = songname;
    }

    public String getArtistname() {
        return Artistname;
    }

    public void setArtistname(String artistname) {
        Artistname = artistname;
    }

    public String getSongUrl() {
        return SongUrl;
    }

    public void setSongUrl(String songUrl) {
        SongUrl = songUrl;
    }
}
