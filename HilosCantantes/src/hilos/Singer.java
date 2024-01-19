package hilos;

import beans.Song;

import java.util.ArrayList;

public class Singer extends Thread {
    private int idSinger;

    private ArrayList<Singer> singers;
    private Song song;
    private int estrofasToSing;
    private static int estrofasToDistribute;
    private static int contadorEstrofas = 0;


    public int getEstrofasToSing() {
        return estrofasToSing;
    }

    public void setEstrofasToSing(int estrofasToSing) {
        this.estrofasToSing = estrofasToSing;
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
            estrofasToSing = song.getEstrofas().size() / singers.size();
            estrofasToDistribute = song.getEstrofas().size() % singers.size();

        } else {
            estrofasToSing = song.getEstrofas().size() / song.getEstrofas().size();
            estrofasToDistribute = 0;
        }

        for (int i = 0; i < estrofasToDistribute; i++) {
            singers.get(i).setEstrofasToSing(singers.get(i).getEstrofasToSing() + 1);
        }

        System.out.println("Estrofas que sobran= " + estrofasToDistribute);

        for (int i = 0; i < getEstrofasToSing(); i++) {
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
                wait(1000);
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
                ", estrofasToSing=" + estrofasToSing +
                ", estrofasToDistribute=" + estrofasToDistribute +
                '}';
    }
}
