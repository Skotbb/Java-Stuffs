//Modified by Scott Thompson 1/27/16
//Modified by Alexander Page 1/25/16



package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * SongCollection.java
 * Read the specified data file and build an array of songs.
 * 
 * Starting code by Prof. Boothe 2015
 * Modfied by Anne Applin 2016
 */

/**
 *
 * @author boothe
 */
public class SongCollection {

    private Song[] songs;

    /**
     * Note: in any other language, reading input inside a class is simply not
     * done!! No I/O inside classes because you would normally provide
     * precompiled classes and I/O is OS and Machine dependent and therefore not
     * portable. Java runs on a virtual machine that IS portable. So this is
     * permissable because we are programming in Java.
     *
     * @param filename The path and filename to the datafile that we are using.
     */
    public SongCollection(String filename) {
        File songsFile;
        Scanner songFileScanner;
        String buffer;
        Song currentSong = new Song();
        ArrayList <Song> songsList = new ArrayList();
        boolean isNewSong = true;
        StringBuilder str = new StringBuilder();
        
        try {
            songsFile = new File(filename);
            songsFile.setReadOnly();
            
            // when told to throw shit, start here
            if (!songsFile.exists())
                System.out.println("File doesn't exist.");
            if (!songsFile.canRead())
                System.out.println("File cannot read.");
            if (songsFile.isDirectory())
                System.out.println("File is a directory.");
            
            songFileScanner = new Scanner(songsFile);
            
            if (songFileScanner.hasNextLine()) {
                while (songFileScanner.hasNextLine()) {
                    buffer = songFileScanner.nextLine();
                    
                    /*
                    If there's a new song entry, it'll always start with the 
                    artist
                    */
                    if (buffer.startsWith("ARTIST=")) {
                        if (!isNewSong) {
                            currentSong.setLyrics(str.toString());
                            songsList.add(currentSong);
                            str.delete(0, str.length());
                            currentSong = new Song();
                        }
                        /*
                        Parses out ARTIST=" and the ending "
                        */
                        buffer = buffer.substring(8, (buffer.length() - 1));
                        
                        currentSong.setArtist(buffer);
                        
                    }
                    else if (buffer.startsWith("TITLE=")) {
                        /*
                        Parses out TITLE=" and the ending "
                        */
                        buffer = buffer.substring(7, (buffer.length() - 1));
                        currentSong.setTitle(buffer);
                        
                    }
                    else if (buffer.startsWith("LYRICS=")) {
                        isNewSong = false;
                        /*
                        Parses out LYRICS=" and the ending "
                        */
                        buffer = buffer.substring(8, (buffer.length()));
                        str.append(buffer);                        
                    }
                    // For the rest of the lyrics
                    else {
                        str.append(buffer).append("\n");
                    }
                }
                currentSong.setLyrics(str.toString());
                songsList.add(currentSong);
                str.delete(0, str.length());
            }
            else
                System.out.println("File is blank.");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        catch (Exception e) {
            System.out.println("Undocumented exception.");
        }
        
        // sort the songs array
        songs = songsList.toArray(new Song[songsList.size()]);
        Arrays.sort(songs, null);    //Added Sort (ST 1/26/16)    
    }
    
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        
        for (Song song : songs) {
            string.append(song.getArtist()).append(" - ").
                    append(song.getTitle()).append("\n");
        }
        
        return string.toString();
    }
 
    /**
     * this is used as the data source for building other data structures
     * @return the songs array
     */
    public Song[] getAllSongs() {
        return songs;
    }
    
    //Modified by Scott Thompson 1/26/16 -added method and tested.
    public void showList(){
        System.out.println("Total songs = " + songs.length);
        if(songs.length < 10){
            for(int i=0; i < songs.length || i < 10; i++){
                System.out.printf("%-20s %s\n",
                    songs[i].getArtist(),songs[i].getTitle());
            }
         }
        else{
            for(int i=0;i < 10; i++){
                System.out.printf("%-20s %s\n",
                    songs[i].getArtist(),songs[i].getTitle());
            }
        }
    }
     /**
     * unit testing method
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog songfile");
            return;
        }
        
        SongCollection sc = new SongCollection(args[0]);
            //sc.showList();    //Testing
        
        /*SongCollection sc = new SongCollection("allSongs.txt");
        sc.showList();*/ //Testing

        // show song count and first 10 songs (name & title only, 1 per line)
            //^ Added as a method for SongCollection class. (ST)
        
    }
        
}