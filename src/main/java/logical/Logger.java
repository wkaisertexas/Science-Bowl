package logical;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

// imports for using JSON files
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Logger { // the function of this class is to log all of the activity that occurred during the game

    public Player[] teamA;
    public Player[] teamB;

    public String savesFolder;

    Logger(String savesFolder){
        this.savesFolder = savesFolder;
    }



    public void logDataToJSON() throws FileNotFoundException {
        // Null and void for now
        JSONObject saveObject = new JSONObject();

        // this is the are that I can add to log a bunch of stuff



        PrintWriter pw = new PrintWriter(generateFilePath());
        pw.write(saveObject.toJSONString());

        pw.flush();
        pw.close();
    }

    // accessor methods
    private String generateFileName(){
        String returnString = LocalDate.now().getYear() + "-";
        returnString += LocalDate.now().getMonth() + "-";
        returnString += LocalDate.now().getDayOfMonth() + ":";
        returnString += "ScienceBowlMatch.json";
        return returnString;
    }

    private String generateFilePath(){
        return savesFolder + "/" + generateFileName();
    }
}
