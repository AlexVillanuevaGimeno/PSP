package main;

import beans.Song;
import hilos.Singer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Singer> singers = new ArrayList<>();
        ArrayList<Song> songs = new ArrayList<>();

        // CREATE BOHEMIAN RHAPSODY
        Song songBohemianRhapsody = new Song(1, "Queen - Bohemian Rhapsody");
        ArrayList<String> estrofasBohemianRhapsody = new ArrayList<>();
        estrofasBohemianRhapsody.add("Is this the real life?");
        estrofasBohemianRhapsody.add("Is this just fantasy?");
        estrofasBohemianRhapsody.add("Caught in a landside,");
        estrofasBohemianRhapsody.add("No escape from reality.");
        estrofasBohemianRhapsody.add("Open your eyes,");
        estrofasBohemianRhapsody.add("Look up to the skies and see,");
        estrofasBohemianRhapsody.add("I'm just a poor boy, I need no sympathy,");
        estrofasBohemianRhapsody.add("Because I'm easy come, easy go,");
        estrofasBohemianRhapsody.add("Little high, little low,");
        estrofasBohemianRhapsody.add("Any way the wind blows doesn't really matter to");
        estrofasBohemianRhapsody.add("to me, to me.");
        // Agrega más estrofas según sea necesario...

        songBohemianRhapsody.setEstrofas(estrofasBohemianRhapsody);

        songs.add(songBohemianRhapsody);

        Scanner scanner = new Scanner(System.in);
        int singerNumber = 0;
        System.out.println("¿CUANTOS CANTANTES HAY?");
        singerNumber = scanner.nextInt();
        scanner.close();

        for (int i = 0; i < singerNumber; i++) {
            Singer singer = new Singer(i);
            singer.setSong(songBohemianRhapsody); // Cambiado a songBohemianRhapsody
            singers.add(singer);
        }

        for (Singer singer : singers) {
            singer.setSingers(singers);
            singer.start();
        }

    }
}
