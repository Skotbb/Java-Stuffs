/* Modified by Scott Thompson 2016
 * Modified by Nicolas DesJardins 2016
 *     I forgot to read the comments :D
 * 
 * Song class to hold strings for a song's artist, title, and lyrics
 * Do not add any methods, just implement the ones that are here.
 * Starting code by Prof. Boothe 2015
 * Modfied by Anne Applin 2016
 */
/**
 *
 * @author boothe
 */
package student;

public class Song implements Comparable<Song> {
    
    private String artist = "unknown";
    private String title = "unknown";
    private String lyrics = "";
    private String tempString = "";
    
    public Song(String artist, String title, String lyrics) {
        this.setArtist(artist);
        this.setTitle(title);
        this.setLyrics(lyrics);
    }
    
    public Song() {
        
    }
    
    public void setArtist(String string) {
        this.artist = string;
    }
    public void setTitle(String string) {
        this.title = string;
    }
    public void setLyrics(String string) {
        this.lyrics = string;
    }
    
    /**
     *
     * @return a String for the Artist's name
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     *
     * @return a string with the song's lyrics.
     */
    public String getLyrics() {
        return this.lyrics;
    }

    /**
     *
     * @return a string with the Song's name/title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     *
     * @return String  in format of: "artist, title"
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(artist).append(", ").append(title).append("\n");
        
        return str.toString();
    }

    /**
     * the default comparison of songs
     * primary key: artist, secondary key: title
     * used for sorting and searching the song array
     * if two songs have the same artist and title they are considered the same
     * @param song2
     * @return -1, 0 or 1 depending on whether this song should be  before, 
     *    after or is the same.  Used for a "natural" sorting order.  In this 
     *    case first by author then by title so that the all of an artist's 
     *    songs are together, but in alpha order.  
     */
    @Override
    public int compareTo(Song song2) {
        // named constants are used for clarity in the code
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        
        if (song2 == null)
            return AFTER; // shouldn't be any null objects, but if there are
                          // put them at the end
        if (this == song2) return EQUAL;  //If songs are the same, set them
                                          //equal
        
            //Compare the Artists first                            
        if (this.artist.compareToIgnoreCase(song2.artist) < 0) return BEFORE;
        if (this.artist.compareToIgnoreCase(song2.artist) > 0) return AFTER;
        
            //Then compare the Titles
        if (this.title.compareToIgnoreCase(song2.title) < 0) return BEFORE;
        if (this.title.compareToIgnoreCase(song2.title) > 0) return AFTER; 
        
        return EQUAL;
    }

 
    /**
     * testing method to unit test this class
     * @param args
     */
    public static void main(String[] args) {
        Song s1 = new Song("Professor B",
                "Small Steps",
                "Write your programs in small steps\n"
                + "small steps, small steps\n"
                + "Write your programs in small steps\n"
                + "Test and debug every step of the way.\n");

        Song s2 = new Song("Brian Dill",
                "Ode to Bobby B",
                "Professor Bobby B., can't you see,\n"
                + "sometimes your data structures mystify me,\n"
                + "the biggest algorithm pro since Donald Knuth,\n"
                + "here he is, he's Robert Boothe!\n");

        Song s3 = new Song("Professor B",
                "Debugger Love",
                "I didn't used to like her\n"
                + "I stuck with what I knew\n"
                + "She was waiting there to help me,\n"
                + "but I always thought print would do\n\n"
                + "Debugger love .........\n"
                + "Now I'm so in love with you\n");

        System.out.println("testing getArtist: " + s1.getArtist());
        System.out.println("testing getTitle: " + s1.getTitle());
        System.out.println("testing getLyrics:\n" + s1.getLyrics());

        System.out.println("testing toString:\n");
        System.out.println("Song 1: " + s1);
        System.out.println("Song 2: " + s2);
        System.out.println("Song 3: " + s3);

        System.out.println("testing compareTo:");
        System.out.println("Song1 vs Song2 = " + s1.compareTo(s2));
        System.out.println("Song2 vs Song1 = " + s2.compareTo(s1));
        System.out.println("Song1 vs Song3 = " + s1.compareTo(s3));
        System.out.println("Song3 vs Song1 = " + s3.compareTo(s1));
        System.out.println("Song1 vs Song1 = " + s1.compareTo(s1));
    }
}
