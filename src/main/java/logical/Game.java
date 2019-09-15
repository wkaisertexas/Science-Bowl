package logical;

import GUI.Reader;
import GUI.Timer;
import com.company.Main;

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
        //TODO: find some way of making it so this loop does not cause a stall somewhere

    // this sets up the question loop(This may not work well with halves)
        for(int questionNumber = 1; questionNumber <= 25 && checkTimeLeft(); questionNumber++){
            // this section of code should update the question number
            r.setQuestionNumber(questionNumber);
            // this should fetch a question from the generator
            questions[questionNumber - 1] = gen.getQuestion();

            // this should update the tossup question on the Reader's page
            r.updateQuestion(questions[questionNumber - 1]);

            // this is where I need to wait for whoever to answer it to answer it









        }

    }

    public void endGame(){


        // this tells the main class that the game has ended
        m.gameComplete();
    }


    public boolean checkTimeLeft(){
        // TODO: Add code here that will be able to check the amount of time left
        return true;
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
