package logical;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Random;

public class Generator {
    // variables declaration

    private double targetDifficulty;

    private Question[] questions;


    // end of variables declaration

    Generator(String databasePath, double targetDifficulty){
        this.targetDifficulty = targetDifficulty;

        loadDatabase(databasePath);
    }

    public Question getQuestion(){
        // this will eventually use some sort of statistical algorithm to approximate question difficulty and then adapt based on that but for now it will just get random question
        Random r = new Random();
        return questions[r.nextInt(questions.length)];
    }

    private void loadDatabase(String databasePath){
        System.out.println("Started Loading Database");
        JSONParser jsonParser = new JSONParser();

        try {
            // Reads the JSON File
            FileReader reader = new FileReader(databasePath);
            Object obj = jsonParser.parse(reader);

            JSONArray questionDatabase = (JSONArray) obj;

            // this creates the question array of a proper size in order to store all of the questions
            questions = new Question[questionDatabase.size()];

            // in this I need a way to parse this data to convert it into a question array
            for(int i = 0; i < questionDatabase.size(); i++){
                questions[i] = new Question((JSONObject) questionDatabase.get(i));
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        System.out.println("Finished loading database");
    }
}
