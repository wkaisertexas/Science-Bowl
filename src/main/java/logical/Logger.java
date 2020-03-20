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

    public Question[] questions;
    public long[] pauses;
    public long[] resumes;

    public String savesFolder;

    Logger(String savesFolder){
        this.savesFolder = savesFolder;
    }

    public void logDataToJSON() throws FileNotFoundException {
        // Null and void for now
        JSONObject saveObject = new JSONObject();

        // Match Details
        String date = LocalDate.now().getYear() + "-" + LocalDate.now().getMonth() + "-" +
                LocalDate.now().getDayOfMonth();
        saveObject.put("date", date);

        // this is the are that I can add to log a bunch of stuff
        JSONArray questionsArray = new JSONArray(); // this is the JSON array of questions I will need to have some more stuff about the match
        for(Question quest : questions){
            questionsArray.add(quest.toJSON());
        }
        saveObject.put("questions", questionsArray);

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
