package logical;

import org.json.simple.JSONObject;

public class Player {  // this is only a very basic class that stores information about a player inside it
    public String firstName;
    public String lastName;
    public int graduationYear;

    public Player(String firstName, String lastName, int graduationYear){
        this.firstName = firstName;
        this.lastName = lastName;
        this.graduationYear = graduationYear;
    }

    // this is an alternate constructor that allows the player class to be loaded for a JSON file
    public Player(JSONObject player){
        // this will do all of the loading scripts for all of the necessary information
        JSONObject playerDetails = (JSONObject) player.get("player"); // the demands a specific file structure for the JSON

        this.firstName = (String) playerDetails.get("firstName");
        this.lastName = (String) playerDetails.get("lastName");
        this.graduationYear = (int) playerDetails.get("graduationYear");
    }

}
