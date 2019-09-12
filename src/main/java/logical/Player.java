package logical;

import org.json.simple.JSONObject;

public class Player {  // this is only a very basic class that stores information about a player inside it
    public String first;
    public String last;
    public int graduationYear;

    // these represent the data about how people are doing in the given game(Tracking variables)
    public int numbInterrupts = 0;
    public int numbBlurts = 0;
    public int numbStalls = 0;
    public int numbTalkingPenalties = 0;

    public int numbIncorrect = 0; // this will only tally tossups
    public int numbCorrect = 0; // this will only will tally tossups

    public Player(String first, String last, int graduationYear){
        this.first = first;
        this.last = last;
        this.graduationYear = graduationYear;
    }

    // this is an alternate constructor that allows the player class to be loaded for a JSON file
    public Player(JSONObject player){
        // this will do all of the loading scripts for all of the necessary information
        this.first = (String) player.get("first");
        this.last = (String) player.get("last");
        this.graduationYear = (int) player.get("graduationYear");
    }

    // accessor methods
    public JSONObject toJSON(){
        JSONObject returnObject = new JSONObject();

        returnObject.put("first", first);
        returnObject.put("last", last);
        returnObject.put("graduation_year", graduationYear);

        // add this at a later time
        return returnObject;
    }


}
