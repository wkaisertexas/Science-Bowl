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

    public Question(JSONObject question){



    }

    private void loadQuestionFromJSON(){
        // this will be convert a JSON object into a question object


    }


}
