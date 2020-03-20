package logical;

import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Question {
    // Variables Declaration

    // Question Variables

        public int questionId;

        public String category;
        public String source;

        // Tossup
        public boolean tossupType; // false is shortAnswer, true is multipleChoice
        public String tossupQuestion;
        public String tossupAnswer;

        // Bonus
        public boolean bonusType; // false is shortAnswer, true is multipleChoice
        public String bonusQuestion;
        public String bonusAnswer;

    // details about the who answered the question

        // Tossup variables
        public Player[] blurter; // most of these will be null

        public Player[] incorrectInterupter; // most of these will be null

        public Player[] staller;

        public Player answeredCorrectly; // most of these will be null

        public Player[] answeredIncorrectly;

        // Bonus Variables
        public boolean bonusCorrect;

    // end of variables declaration

    Question(JSONObject q){
        loadQuestionFromJSON(q);
    }

    // accessor methods
    public String tossupTypeToString(){
        if(tossupType){
            return "Multiple Choice";
        } else{
            return "Short Answer";
        }
    }

    public String bonusTypeToString(){
        if(bonusType){
            return "Multiple Choice";
        } else{
            return "Short Answer";
        }
    }

    private void loadQuestionFromJSON(JSONObject q){
        questionId = ((Long) q.get("id")).intValue();

        category = (String) q.get("category");
        source = (String) q.get("source");

        tossupType = ((String) q.get("tossup_format")).equals("Multiple Choice");
        tossupQuestion = (String) q.get("tossup_question");
        tossupAnswer = (String) q.get("tossup_answer");

        bonusType = ((String) q.get("bonus_format")).equals("Multiple Choice");
        bonusQuestion = (String) q.get("bonus_question");
        bonusAnswer = (String) q.get("bonus_answer");
    }

    public JSONObject toJSON(){ // this is how data will be stored in this time by storing it in a json question object array(Null for now)
        JSONObject returnQuestion = new JSONObject();

        returnQuestion.put("id", questionId);

        returnQuestion.put("category", category);
        returnQuestion.put("source", source);

        // I might want to create a smaller json object to add to the json object called tossup
        JSONObject tossup = new JSONObject();
        tossup.put("type", tossupTypeToString());
        tossup.put("question", tossupQuestion);
        tossup.put("answer", tossupAnswer);
        returnQuestion.put("tossup", tossup);

        JSONObject bonus = new JSONObject();
        bonus.put("type", bonusTypeToString());
        bonus.put("question", bonusQuestion);
        bonus.put("answer", bonusAnswer);
        returnQuestion.put("bonus", bonus);

        JSONObject playerInfo = new JSONObject();

        JSONObject penalties = new JSONObject();
        // Add all of the player and timing variables
        if(blurter != null){
            JSONArray bluters = new JSONArray();
            for(Player blt: blurter){
                bluters.add(blt.toJSON());
            }
            penalties.put("blurt", bluters);
        }

        if(incorrectInterupter != null){
            JSONArray incorrectInterupters = new JSONArray();
            for(Player inc: incorrectInterupter){
                incorrectInterupters.add(inc.toJSON());
            }
            penalties.put("interupt", incorrectInterupters);
        }

        if(staller != null){
            JSONArray stallers = new JSONArray();
            for(Player stl : staller){
                stallers.add(stl.toJSON());
            }
            penalties.put("stall", stallers);
        }
        playerInfo.put("penalties", penalties);


        JSONObject answering = new JSONObject();
        // Correct answers
        if(answeredCorrectly != null){
            answering.put("correct", answeredCorrectly.toJSON());
        }else{
            System.err.println("Error: No person logged for correctly answering question id: " + questionId);
        }

        // Incorect answers
        if(answeredIncorrectly != null){
            JSONArray incorrect = new JSONArray();
            for(Player inc : answeredIncorrectly){
                incorrect.add(inc.toJSON());
            }
            answering.put("incorrect", incorrect);
        }
        playerInfo.put("answering", answering);

        returnQuestion.put("playerInfo", playerInfo);

        return returnQuestion;
    }
}
