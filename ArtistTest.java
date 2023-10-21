import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ArtistTest {
    private Artist artist;
    
    @Before
    public void setUp() {
      artist = new Artist("569KZMFR_%", "05-11-1980", "Melbourne|Victoria|Australia",
            "Talented singer and Love secer and currently living in london ",
            "Singer, Songwriter", "2022, Thes best Singer of Austrailia", "rock,Sad, jazz");
    }
    @Test
  
    public void testAddValidArtist() {
        assertTrue(artist.addArtist());
    }
    //putting invalid id to check it 
    @Test
    public void testUpdateInvalidArtist() {
        Artist invalidArtist = new Artist("InvalidID", "01-01-1990", "City|State|Country",
                "Sample bio text", "Occupation1, Occupation2", "2000, Award 1, 2005, Award 2", "Genre1, Genre2");
        assertFalse(invalidArtist.updateArtist());
    }
    //putting invalid genre
    @Test
    public void testInvalidGenres() {
        Artist invalidGenresArtist = new Artist("569KZMFR_%", "01-01-1990", "City|State|Country",
                "Sample bio text", "Occupation1, Occupation2", "2000, Award 1 is from Austrailia, 2005, Award 2", "pop,rock, Genre2");
                //it will return false
        assertFalse(invalidGenresArtist.updateArtist());
    }
    @Test
    //addding invalid address
    public void checkInvalidAddress(){
        artist = new Artist("569KZFFR_%", "05-11-1980", "Melbourne,Victoria,Australia",
            "Talented singer and Love secer and currently living in london",
            "Singer, Songwriter, Lyricist", "2022, Best Singer of all World", "dick,sex, jazz");
            assertFalse(artist.addArtist());
    }
    @Test
    //addming invalid id 
    public void checkInvalidArtistId(){
            Artist invalidID = new Artist("1234567", "01-01-1990", "City|State|Country",
                "Sample bio text due to some issues i have dww", "Occupation1, Occupation2", "2000, Award 1 is from Austrailia, 2005, Award 2", "pop,Genre1, Genre2");
                assertFalse(invalidID.addArtist());

    }

    @Test 
    //adding more than valid awards
    public void checkInvalidAwards(){
        Artist invalidID = new Artist("569KZFFR_%", "01-01-1990", "City|State|Country",
                "Sample bio text is written here due to some issues ", "Occupation1, Occupation2",
                 "2000, Award 1 is from Austrailia, 2000, Award 1 is from Austrailia,2000, Award 1 is from Austrailia,2002, Award 1 is from Austrailia,2004, Award 1 is from Austrailia", "pop,Genre1, Genre2");
                 //it will return false
                assertFalse(invalidID.addArtist());
    }
    @Test
    //chaning award before 2000 
    public void checkChangingAwardBefore2000(){
        artist = new Artist("569KKKRR_%", "05-11-1980", "Melbourne|Victoria|Australia",
            "Talented singer and Love secer and currently living in london",
            "Singer, Songwriter", "2022, Thes best Singer of Austrailia", "rock,Sad, jazz");
            //it will not change and return false
            assertFalse(artist.updateArtist());
    }
}
