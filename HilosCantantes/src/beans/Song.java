package beans;

import java.util.ArrayList;

public class Song {
    int idSong;
    String title;
    ArrayList<String> estrofas;

    public Song(int idSong, String title){
        setIdSong(idSong);
        setTitle(title);
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getEstrofas() {
        return estrofas;
    }

    public void setEstrofas(ArrayList<String> estrofas) {
        this.estrofas = estrofas;
    }
}
