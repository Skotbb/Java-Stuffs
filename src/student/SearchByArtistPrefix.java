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
        Song key = new Song(artistPrefix, "", "");
        Comparator<Song> cmp = new Song.CmpArtist();
        int partLength = artistPrefix.length();
        int i = Arrays.binarySearch(songs, key, cmp);
        // artist comparison counter
        int count=((CmpCnt)cmp).getCmpCnt();
        ArrayList<Song> list = new ArrayList<>();
        
        if(i < 0){
            i = -i - 1;
        }
        
        if(i>0){
            
            while(songs[i].getArtist().substring(0, partLength).compareToIgnoreCase(artistPrefix)==0
                    && songs[i-1].getArtist().length() >= partLength){
                i--;
            }
            // this fixes the santana issue
                if(songs[i-1].getArtist().length() >= partLength){
                i++; // for some reason this line was causing it to not display all 71 one songs(missing the first song in the list)
            }
                
                while(songs[i].getArtist().substring(0, partLength).compareToIgnoreCase(artistPrefix) == 0
                    && songs[i+1].getArtist().length() >= partLength){
                list.add(songs[i]);
                
                if(i == songs.length-2){    //If near the end, manually add last element
                    list.add(songs[i+1]); System.out.println("i4= " + i);   //Keeps while loop from going out of bounds
                    i = 1;                  //Resets i to kick out of loop.
                }
                else
                    i++;
                }
                // artist comparison counter for loop one
                System.out.println("artist comparisons loop one: " + count);
                // reset the counter
                ((CmpCnt)cmp).resetCmpCnt();

            Song[] result = new Song[list.size()];
            result = list.toArray(result);
            return result;
                }
        
        else if(i == 0){    //Already at beginning of list, so start compiling matches
            while(songs[i].getArtist().substring(0, partLength).compareToIgnoreCase(artistPrefix) == 0){
                list.add(songs[i]);
                i++;
                
            }
                // artist comparison counter for loop two
                System.out.println("artist comparisons loop two: " + count);
                // reset the counter
                ((CmpCnt)cmp).resetCmpCnt();
            Song[] result = new Song[list.size()];
            result = list.toArray(result);
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
        SongCollection sc = new SongCollection("allSongs.txt");
        SearchByArtistPrefix sbap = new SearchByArtistPrefix(sc);
        
        System.out.println("searching for: Beatles");
        Song[] byArtistResult = sbap.search("beatles");
            
//        System.out.println("searching for: santana");
//        Song[] byArtistResult = sbap.search("santana");

//        System.out.println("searching for: arlo");
//        Song[] byArtistResult = sbap.search("arlo");
    
//        System.out.println("searching for: a");
//        Song[] byArtistResult = sbap.search("a");
        
//        System.out.println("searching for: z");
//        Song[] byArtistResult = sbap.search("z");
        
//        System.out.println("searching for: x");
//        Song[] byArtistResult = sbap.search("x");
        
        if(byArtistResult.length > 0){
            System.out.println("Total number of songs: "+byArtistResult.length+"\n");
            for(int i = 0;i<10 && i<byArtistResult.length;i++){
                System.out.println(byArtistResult[i].getArtist()+"   "+byArtistResult[i].getTitle());
            }
        }
        else if(byArtistResult.length == 0){
            System.out.println("No results found");
        }
    }
    }
    
    