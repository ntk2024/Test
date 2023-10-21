

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File; // Import File class
import java.io.IOException;
import java.util.regex.Pattern;

public class Artist {
    private String artistID;
    private String birthDate;
    private String address;
    private String bio;
    private String occupations;
    private String awards;
    private String genres;
    //constructor of Artist class
    public Artist(String artistID, String birthDate, String address, String bio, String occupations, String awards, String genres) {
        this.artistID = artistID;
        this.birthDate = birthDate;
        this.address = address;
        this.bio = bio;
        this.occupations = occupations;
        this.awards = awards;
        this.genres = genres;
    }
//add Artist 
    public boolean addArtist() {
        //checking all the valid conditions
        if (isValidArtistID(artistID) &&
            isValidBirthDate(birthDate) &&
            isValidAddress(address) &&
            isValidBio(bio) &&
            isValidOccupations(occupations) &&
            isValidAwards(awards) &&
            isValidGenres(genres)) {
            try {
                //writing the data to the file
                FileWriter writer = new FileWriter("artists.txt", true);
                writer.write(artistID + "|" + birthDate + "|" + address + "|" + bio + "|" + occupations + "|" + awards + "|" + genres + "\n");
                //
                writer.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean updateArtist() {
        //checking all the valid conditions
        if (isValidArtistID(artistID) &&
            isValidBirthDate(birthDate) &&
            isValidAddress(address) &&
            isValidBio(bio) &&
            isValidOccupations(occupations) &&
            isValidGenres(genres)) {
            try {
                //opening two files
                String filePath = "artists.txt";
                String tempFilePath = "temp_artists.txt";
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath));
                String line;
                boolean artistUpdated = false;
                //concatinating the string to write in the file
                String newArtistData = artistID + "|" + birthDate + "|" + address + "|" + bio + "|" + occupations + "|" + awards + "|" + genres;
                while ((line = reader.readLine()) != null) {
                    //splitting the string
                    String[] parts = line.split("\\|");
                    String currentArtistID = parts[0];
                    //checking artist id to update
                    if (currentArtistID.equals(artistID)) {
                        String[] existingAwards = parts[7].split(",");
                        //getting date of birth adn awards
                        String[] dob = parts[1].split("-");
                        System.out.println("Date of Birth (DOB): " + dob[2]);
                        boolean awardsValid = true;
                        for (int i = 0; i < existingAwards.length; i += 2) {
                            String awardYear = existingAwards[i].trim();
                            System.out.println(awardYear);
                            try {
                                // Attempt to parse the award year as an integer
                                int year = Integer.parseInt(awardYear);
                                if (year < 2000) {
                                    awardsValid = false; // Cannot change awards before 2000
                                }
                            } catch (NumberFormatException e) {
                                awardsValid = false; // Award year is not a valid integer
                            }
                        }
                        if (!awardsValid) {
                            reader.close();
                             writer.close();
                            return false; // Invalid awards
                        }
                        System.out.println(dob[2]+" "+parts[6]);
                        //validation for chaing occupation
                        if(Integer.parseInt(dob[2])<2000&&parts[6].equalsIgnoreCase(occupations)){
                            reader.close();
                            writer.close();
                            return false;
                        }
                        writer.write(newArtistData);
                        artistUpdated = true;
                    } else {
                        writer.write(line);
                    }
                    writer.newLine();
                }
                reader.close();
                writer.close();
                File fileToDelete = new File("artists.txt");
                fileToDelete.delete();
                new File(tempFilePath).renameTo(new File(filePath));
                return artistUpdated;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    
        System.out.println("Invalid data");
        return false;
    }
    
    //some helper validation functions

    private boolean isValidArtistID(String artistID) {
        //regix to check artist id 
        return artistID.matches("[5-9]{3}[A-Z]{5}[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{2}");
    }

    private boolean isValidBirthDate(String birthDate) {
        return Pattern.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-[0-9]{4}$", birthDate);
    }

    private boolean isValidAddress(String address) {
        return Pattern.matches("^[A-Za-z ]+\\|[A-Za-z ]+\\|[A-Za-z ]+$", address);
    }

    private boolean isValidBio(String bio) {
        int wordCount = bio.split("\\s+").length;
        return wordCount >= 10 && wordCount <= 30;
    }

    private boolean isValidOccupations(String occupations) {
        String[] occupationList = occupations.split(",");
        return occupationList.length >= 1 && occupationList.length <= 5;
    }

    public static boolean isValidAwards(String awards) {
        //checking valid awards
        String[] awardList = awards.split(",");
        if (awardList.length % 2 == 0 && awardList.length <= 6) {
            for (int i = 0; i < awardList.length; i += 2) {
                String year = awardList[i].trim();
                String title = awardList[i + 1].trim();
                if (!Pattern.matches("^\\d{4}$", year) || title.split("\\s+").length < 4 || title.split("\\s+").length > 10) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidGenres(String genres) {
        String[] genreList = genres.split(",");
        if (genreList.length >= 2 && genreList.length <= 5) {
            int popCount = 0;
            int rockCount = 0;
            for (String genre : genreList) {
                if (genre.trim().equalsIgnoreCase("pop")) {
                    popCount++;
                }
                if (genre.trim().equalsIgnoreCase("rock")) {
                    rockCount++;
                }
                if (popCount > 0 && rockCount > 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        //main function to test the following functions ;
        Artist artist = new Artist("569KKKRR_%", "05-11-1980", "Melbourne|Victoria|Australia",
        "Talented singer working in La for singing purpose and doing nothing else and currently living in London",
        "Singer, Songwriter", "2001, Best Singer of America", "hipHop,rap, jazz");
    System.out.println(artist.updateArtist());
            // System.out.println(artist.updateArtist());
       
    }
}
