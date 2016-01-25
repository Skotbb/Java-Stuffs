//Modified by Alexander Page 1/24/16

/*
================================================================================
--------------------------------------------------------------------------------
Okay, so. I've made a lot of changes to the file. I've created a stringbuilder
for the lyrics and it seems to work okay on my end. I've also added a few things
and moved around a few lines to work with what I thought would work for the 
project. I also hardcoded in the filename for the reader because I could not get
it to work in the File>Project Properties>Run>Arguments thingy. I've tested it
out and it still gets an error at the very end after processing through all of
the shortSongs.txt file. If either of you could take a look at it while I am 
at work tomorrow that would be pretty rad. I get out of work tomorrow(Monday) at
4:00pm. You can reach me in case of emergency or if you despirately need to 
tell me that I am an idiot on my cellphone, (207)841-7612. -Alexander Page 1/24/16
--------------------------------------------------------------------------------
================================================================================
*/


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
        String buffer, lyrics;
        Song currentSong = new Song("","","");
        ArrayList <Song> songsList = new ArrayList();
        
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
                while (songFileScanner.hasNextLine()) {
                    StringBuilder str = new StringBuilder();
                    buffer = songFileScanner.nextLine();
                    
                    /*
                    If there's a new song entry, it'll always start with the 
                    artist
                    */
                    if (buffer.startsWith("ARTIST=")) {
                        System.out.println("Parsing artists");
                        
                        //currentSong = songs[songs.length - 1]; <-- this is
                        //just setting currentSong to null because songs[] is
                        //never actually filled before this. -Alexander Page
                        /*
                        Parses out ARTIST=" and the ending "
                        */
                        buffer = buffer.substring(8, (buffer.length() - 1));
                        
                        currentSong.setArtist(buffer);
                        
                    }
                    else if (buffer.startsWith("TITLE=")) {
                        System.out.println("Parsing titles");
                        /*
                        Parses out TITLE=" and the ending "
                        */
                        buffer = buffer.substring(7, (buffer.length() - 1));
                        currentSong.setTitle(buffer);
                        
                    }
                    else if (buffer.startsWith("LYRICS=")) {
                        System.out.println("Parsing lyrics");
                        /*
                        Parses out LYRICS=" and the ending "
                        */
                        buffer = buffer.substring(8, (buffer.length()));
                        str.append(buffer);
                        
                    }
                    // For the rest of the lyrics
                    else {
                        System.out.println("Parsing lyrics still");
                        str.append(buffer);
                        if(songFileScanner.nextLine().startsWith("ARTIST=")){
                            lyrics = str.toString();
                            currentSong.setLyrics(lyrics); 
                            songsList.add(currentSong);
                           
                        }
                    }
                    
                }
                songs = (Song[]) songsList.toArray();
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