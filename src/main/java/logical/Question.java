package logical;

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
        public Player blurter; // most of these will be null

        public Player incorrectInterupter; // most of these will be null

        public Player staller;

        public Player answeredCorrectly; // most of these will be null

        public Player answeredIncorrectly;

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

    public JSONObject getSaveableQuestion(){ // this is how data will be stored in this time by storing it in a json question object array(Null for now)
        // TODO: Complete saveable object class

        return null;
    }
}
