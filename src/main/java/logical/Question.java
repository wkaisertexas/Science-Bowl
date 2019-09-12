package logical;

import org.json.simple.JSONObject;

public class Question {
    // Variables Declaration

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

}
