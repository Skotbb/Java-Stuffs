//Modified by Alexander Page 1/25/16



package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        
        try {
            songsFile = new File("shortSongs.txt");
            
            // when told to throw shit, start here
            if (!songsFile.exists())
                System.out.println("File doesn't exist.");
            if (!songsFile.canRead())
                System.out.println("File cannot read.");
            if (songsFile.isDirectory())
                System.out.println("File is a directory.");
            
            songFileScanner = new Scanner(songsFile);
            
            if (songFileScanner.hasNextLine()) {
                StringBuilder str = new StringBuilder();
                while (songFileScanner.hasNextLine()) {
                    buffer = songFileScanner.nextLine();
                    
                    /*
                    If there's a new song entry, it'll always start with the 
                    artist
                    */
                    if (buffer.startsWith("ARTIST=")) {
                        if (!isNewSong) {
                            currentSong.setLyrics(str.toString());
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
                        System.out.println(str);
                        
                    }
                    // For the rest of the lyrics
                    else {
                        str.append(buffer);
                    }
                    
                    songsList.add(currentSong);
                    
                }
                currentSong.setLyrics(str.toString());
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
	// use a try catch block
        // read in the song file and build the songs array
        // you must use a StringBuilder to read in the lyrics!
        
        
        
        // sort the songs array
    }
 
    /**
     * this is used as the data source for building other data structures
     * @return the songs array
     */
    public Song[] getAllSongs() {
        return songs;
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

        // todo: show song count and first 10 songs (name & title only, 1 per line)
    }
}