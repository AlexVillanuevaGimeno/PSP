package hilos;

import beans.Song;

import java.util.ArrayList;

public class Singer extends Thread {
    private int idSinger;

    private ArrayList<Singer> singers;
    private Song song;
    private int estrofasParaCantar;
    private static int estrofasDistribuidas;
    private static int contadorEstrofas = 0;


    public int getestrofasParaCantar() {
        return estrofasParaCantar;
    }

    public void setestrofasParaCantar(int estrofasParaCantar) {
        this.estrofasParaCantar = estrofasParaCantar;
    }

    public Singer(int idSinger) {
        this.idSinger = idSinger;
    }

    public int getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(int idSinger) {
        this.idSinger = idSinger;
    }

    public ArrayList<Singer> getSingers() {
        return singers;
    }

    public void setSingers(ArrayList<Singer> singers) {
        this.singers = singers;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
    @Override
    public synchronized void run() {
        if (singers.size() < song.getEstrofas().size()) {
            estrofasParaCantar = song.getEstrofas().size() / singers.size();
            estrofasDistribuidas = song.getEstrofas().size() % singers.size();

        } else {
            estrofasParaCantar = song.getEstrofas().size() / song.getEstrofas().size();
            estrofasDistribuidas = 0;
        }

        for (int i = 0; i < estrofasDistribuidas; i++) {
            singers.get(i).setestrofasParaCantar(singers.get(i).getestrofasParaCantar() + 1);
        }

        System.out.println("Estrofas que sobran= " + estrofasDistribuidas);

        for (int i = 0; i < getestrofasParaCantar(); i++) {
            if (getIdSinger() == 0 && contadorEstrofas == 0) {
                System.out.println("Cantante: " + this.getIdSinger() + ", esta cantando la  estrofa:" +
                        (contadorEstrofas + 1) + ", " + song.getEstrofas().get(contadorEstrofas));
                if (getIdSinger() == singers.size() - 1) {
                    contadorEstrofas++;
                    singers.get(0).interrupt();
                } else {
                    contadorEstrofas++;
                    singers.get(getIdSinger() + 1).interrupt();
                }
                continue;
            }

            try {
                join(1000);
            } catch (InterruptedException e) {
                if (getIdSinger() >= song.getEstrofas().size()) {
                    for (Singer singer : singers) {
                        singer.interrupt();
                    }
                } else {
                    System.out.println("Cantante: " + this.getIdSinger() + ", esta cantando la  estrofa:" + (contadorEstrofas + 1) + ", " + song.getEstrofas().get(contadorEstrofas));
                    if (getIdSinger() == singers.size() - 1) {
                        contadorEstrofas++;
                        singers.get(0).interrupt();
                    } else {
                        contadorEstrofas++;
                        singers.get(getIdSinger() + 1).interrupt();
                    }
                }
            }
        }

    }



    public String toStrings() {
        return "Singer{" +
                "idSinger=" + idSinger +
                ", singersSize=" + singers.size() +
                ", songTitle=" + song.getTitle() +
                ", estrofasParaCantar=" + estrofasParaCantar +
                ", estrofasDistribuidas=" + estrofasDistribuidas +
                '}';
    }
}
