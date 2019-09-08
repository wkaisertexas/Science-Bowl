package logical;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Generator {
    // variables declaration

    public double targetDifficulty;

    // end of variables declaration

    public Generator(String databasePath, double targetDifficulty){
        this.targetDifficulty = targetDifficulty;

        loadDatabase(databasePath);
    }

    public Question generateQuestion(){ // blank for now
        return null;

    }




    private void loadDatabase(String databasePath){
        JSONParser jsonParser = new JSONParser();

        try {
            // Reads the JSON File
            FileReader reader = new FileReader(databasePath);
            Object obj = jsonParser.parse(reader);

            JSONArray questionDatabase = (JSONArray) obj;

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }




}
