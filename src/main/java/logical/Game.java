package logical;

import GUI.Reader;
import GUI.Timer;
import com.company.Main;

import java.util.ArrayList;

public class Game { // this will store all of the relevant information to a game

    public Player[] aTeam;
    public Player[] bTeam;

    public Generator gen;

    // this is all of the GUI variables
    public Reader r;
    public Timer t;

    private Main m;

    private Question[] questions = new Question[25]; /// this will be null for now because this will allow continuous iteration over the the round
    private int questionNumber; // this is an integer from 1 to 25

    public int teamAPosScore = 0;
    public int teamANegScore = 0;

    public int teamBPosScore = 0;
    public int teamBNegScore = 0;

    public int startTime;
    public int[] pausingPoints;
    public int[] resumingPoints;

    public Game(Player[] aTeam, Player[] bTeam, String databasePath, String saveLocationPath, double targetDifficulty, Main m){
        // this saves the player array
        this.aTeam = aTeam;
        this.bTeam = bTeam;

        gen = new Generator(databasePath, targetDifficulty);

        this.r = new Reader(aTeam, bTeam, this);
        this.t = new Timer();

        this.m = m;
    }

    public void startGame(){
        questionNumber = 1;
        startTime = System.currentTimeMilis();
        nextQuestion();
    }

    public void action(String action){
        // This needs to sort the string in order to see what kind of action it is


    }

    public void nextQuestion(){
        if(questionNumber > 25 && !checkTimeLeft()){
            endGame();
            return;
        }

        // this section of code should update the question number
        r.setQuestionNumber(questionNumber);
        // this should fetch a question from the generator
        questions[questionNumber - 1] = gen.getQuestion();

        // this should update the tossup question on the Reader's page
        r.updateQuestion(questions[questionNumber - 1]);

    }

    public void endGame(){


        // this tells the main class that the game has ended
        m.gameComplete();
    }

    public boolean pauseOrResumeGame(){
        // For the prevention of errors
        if(this.startTime == null){
            System.err.println("Start Time null unable to pause game");
            return false;
        }

        if(this.pausingPoints != null){
            if(this.resumingPoints != null) {
                if(pausingPoints.length > resumingPoints.length){
                    // resume
                    int[] newResumePoints = new int[resumingPoints.length + 1];
                    for(int i = 0; i < resumingPoints.length; i++){
                        newResumePoints[i] = resumingPoints[i];
                    }
                    newResumePoints[resumingPoints.length] = System.currentTimeMilis() - startTime; // TODO: Think about changing this to current time milis() - start time
                    resumingPoints = newResumePoints;
                    return false;
                }else{
                    // pause
                    int[] newPausePoints = new int[pausingPoints.length + 1];
                    for(int i = 0; i < pausingPoints.length; i++){
                        newPausePoints[i] = resumingPoints[i];
                    }
                    newPausePoints[pausingPoints.length] = System.currentTimeMilis() - startTime;
                    pausingPoints = newPausePoints;
                    return true;
                }
            }else{
                resumingPoints = new int[]{System.currentTimeMilis() - startTime};
                return false;
            }
        }else{
            pausingPoints = new int[]{System.currentTimeMilis() - startTime};
            return true; // true is for paused false in not
        }
    }


    public boolean checkTimeLeft(){
        return getTimeMilis() < 8 * 60 * 1000;
    }

    public int getTimeMilis(){
        if(pausingPoints != null){
            if(resumingPoints != null){
                // This needs to check to see if the pauses and resumes are balanced
                int pausingPointsSum = 0;
                for(int point : pausingPoints){
                    pausingPointsSum += point;
                }

                int resumingPointsSum = 0;
                for(int point : resumingPoints){
                    resumingPointsSum += point;
                }

                if(pausingPoints.length > resumingPoints.length){
                    // resumed
                    return pausingPointsSum - resumingPointsSum;
                }else{
                    // paused
                    // this needs to take the sum of pausing points minus the sum of resuming points plus the sum of pausing points plus the current system time
                    return pausingPointsSum - resumingPointsSum + System.currentTimeMilis();
                }
            }else{
                return pausingPoints[0] - startTime;
            }
        }else{
            return System.currentTimeMilis() - startTime;
        }
    }

    public void updateScores(){
        // this will update the scores for the Reader
        r.updateTeamAScore(getTeamAScore());
        r.updateTeamBScore(getTeamBScore());

        // this will update the scores for the Timer
        t.updateTeamAScore(getTeamAScore());
        t.updateTeamBScore(getTeamBScore());
    }

    public void updateTeamAScore(int score){
        if(score > 0){
            teamAPosScore += score;
        } else{
            teamANegScore += score;
        }
        updateScores();
    }

    public void updateTeamBScore(int score){
        if(score > 0){
            teamBPosScore += score;
        } else{
            teamBNegScore += score;
        }
        updateScores();
    }

    // summary statistics(This will contain methods that will get general summary statistics for the game)

    public int getTeamAScore(){
        return teamAPosScore + teamBNegScore;
    }

    public int getTeamBScore(){
        return teamBPosScore + teamANegScore;
    }

}
