package student;

import java.io.*;
import java.util.*;

/**
 * SearchByArtistPrefix.java 
 * starting code Boothe 2015 
 * revised by aapplin
 */
public class SearchByArtistPrefix {
    // keep a direct reference to the song array
    private Song[] songs;  

    /**
     * constructor initializes the property.
     * @param sc a SongCollection object
     */
    public SearchByArtistPrefix(SongCollection sc) {
        songs = sc.getAllSongs();
    }

    /**
     * find all songs matching artist prefix uses binary search should operate
     * in time log n + k (# matches)
     *
     * @param artistPrefix all or part of the artist's name
     * @return an array of songs by artists with substrings that match 
     *    the prefix
     */
    public Song[] search(String artistPrefix) {
        // write this method
        artistPrefix = artistPrefix.toLowerCase();
        Song key = new Song(artistPrefix,"","");
        //int count = 0, countTwo = 0;
       
        Comparator<Song> cmp = new Song.CmpArtist();
        int partLength = artistPrefix.length();
        System.out.println("partlength:" +partLength);
        System.out.println("Songs Size: " + songs.length);
        int i = Arrays.binarySearch(songs, key, cmp);
        //new
        //int count=((CmpCnt)cmp).getCmpCnt();
        //end
        
        if(i<0){
            i = -i - 1;
        }
        ArrayList<Song> list = new ArrayList<>();
        if(i>0){
            
            while(songs[i].getArtist().substring(0, partLength).compareToIgnoreCase(artistPrefix) == 0
                    && songs[i-1].getArtist().length() >= partLength){  //Check that Artist letter count isn't smaller than partLength before comparing
                i--;
            }
            i++;
            //System.out.println("artist comparisons loop one: " + countTwo);

            while(songs[i].getArtist().substring(0, partLength).compareToIgnoreCase(artistPrefix) == 0
                    && partLength <= songs[i+1].getArtist().length() && i+1 <= songs.length){
                list.add(songs[i]);
                
                if(i == songs.length-2){    //If near the end, manually add last element
                    list.add(songs[i+1]); System.out.println("i4= " + i);   //Keeps while loop from going out of bounds
                    i = 1;                  //Resets i to kick out of loop.
                }
                else
                    i++;
                
            }
            
            Song[] result = new Song[list.size()];
            result = list.toArray(result);
            
            //System.out.println("artist comparisons loop: " + count);
            return result;
        }
        else if(i == 0){    //Already at beginning of list, so start compiling matches
            while(songs[i].getArtist().substring(0, partLength).compareToIgnoreCase(artistPrefix) == 0){
                list.add(songs[i]);
                i++;
            }
            
            Song[] result = new Song[list.size()];
            result = list.toArray(result);
            
            //System.out.println("artist comparisons loop: " + count);
            return result;
        }
        else{
            return null;
        } 
    }

    /**
     * testing method for this unit
     * @param args  command line arguments
     */
    public static void main(String[] args) {
        //if (args.length == 0) {
        //    System.err.println("usage: prog songfile [search string]");
        //    return;
        //}
        try{
        //SongCollection sc = new SongCollection(args[0]);
        SongCollection sc = new SongCollection("allSongs.txt");
        SearchByArtistPrefix sbap = new SearchByArtistPrefix(sc);
        
        //if (args.length > 1) {
            //System.out.println("searching for: " + args[1]);
            System.out.println("searching for: santana");
            //Song[] byArtistResult = sbap.search(args[1]);
            Song[] byArtistResult = sbap.search("santana");
            
            // to do: show first 10 matches
            
            System.out.println("Total number of songs: "+byArtistResult.length+"\n");
            for(int i = 0;i<10;i++){
            System.out.println(byArtistResult[i].getArtist()+"   "+byArtistResult[i].getTitle());
            
            }
            
        //}
        
        } catch (FileNotFoundException ex) {
            
            System.out.println("File not found");
            System.exit(1);
        }
        
        }
    }

