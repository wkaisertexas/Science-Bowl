package logical;

import GUI.Reader;
import GUI.TimerDisplay;

import java.util.Timer;
import java.util.TimerTask;

public class Game { // this will store all of the relevant information to a game

    public Player[] aTeam;
    public Player[] bTeam;

    public Generator gen;

    // this is all of the GUI variables
    public Reader r;
    public TimerDisplay t;

    private Main m;

    private Question[] questions = new Question[25]; /// this will be null for now because this will allow continuous iteration over the the round
    private int questionNumber; // this is an integer from 1 to 25

    public int teamAPosScore = 0;
    public int teamANegScore = 0;

    public int teamBPosScore = 0;
    public int teamBNegScore = 0;


    // New Way
    public Timer roundTimer;
    public TimerTask updateTime;

    public Timer bonusTimer;

    // Old Way
    public long startTime;
    public long[] pausingPoints;
    public long[] resumingPoints;

    public long tossupStartTime = -1;
    public long bonusStartTime = -1;

    public Game(Player[] aTeam, Player[] bTeam, String databasePath, String saveLocationPath, double targetDifficulty, Main m){
        // this saves the player array
        this.aTeam = aTeam;
        this.bTeam = bTeam;

        gen = new Generator(databasePath, targetDifficulty);

        // Creation of timer tasks
        updateTime = new TimerTask(){
          public void run(){
              if(tossupStartTime != -1){
                  r.setTimer(5 + (tossupStartTime - getTimeMillis()) / 1000);
                  t.setTimer(5 + (tossupStartTime - getTimeMillis()) / 1000);
              }else if(bonusStartTime != -1){
                  r.setTimer(20 + (bonusStartTime - getTimeMillis()) / 1000);
                  t.setTimer(20 + (bonusStartTime - getTimeMillis()) / 1000);
              }else{
                  long timeMillis = 8 * 60 - getTimeMillis() / 1000;
                  r.setTimer(timeMillis);
                  t.setTimer(timeMillis);
              }
          }
        };

        this.r = new Reader(aTeam, bTeam, this);
        this.t = new TimerDisplay();

        this.m = m;
    }

    public void startGame(){
        questionNumber = 0; // This starts at zeros so it can be incremented later
        startTime = System.currentTimeMillis();
        roundTimer = new Timer("Round Timer");
        roundTimer.scheduleAtFixedRate(updateTime, 30, 100);
        // this pauses the game to start off with
        pauseOrResumeGame();

        nextQuestion();
    }

    public void nextQuestion(){
        questionNumber++;

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

    public boolean pauseOrResumeGame(){ // TODO: Maybe add code so that this will log the pauses and starts to the data logger.
        if(pausingPoints != null){
            if(resumingPoints != null) {
                if(pausingPoints.length > resumingPoints.length){
                    // resume
                    long[] newResumePoints = new long[resumingPoints.length + 1];
                    for(int i = 0; i < resumingPoints.length; i++){
                        newResumePoints[i] = resumingPoints[i];
                    }
                    newResumePoints[resumingPoints.length] = System.currentTimeMillis() - startTime;
                    resumingPoints = newResumePoints;
                    return false;
                }else{
                    // pause
                    long[] newPausePoints = new long[pausingPoints.length + 1];
                    for(int i = 0; i < pausingPoints.length; i++){
                        newPausePoints[i] = resumingPoints[i];
                    }
                    newPausePoints[pausingPoints.length] = System.currentTimeMillis() - startTime;
                    pausingPoints = newPausePoints;
                    return true;
                }
            }else{
                resumingPoints = new long[]{System.currentTimeMillis() - startTime};
                return false;
            }
        }else{
            pausingPoints = new long[]{System.currentTimeMillis() - startTime};
            return true; // true is for paused false in not
        }
    } // NOTE: This only modifies the game time I need to add to it to make it modify the tossup and bonus times

    public boolean checkTimeLeft(){
        return getTimeMillis() < 8 * 60 * 1000;
    }

    public long getTimeMillis(){
        if(pausingPoints != null){
            if(resumingPoints != null){
                // This needs to check to see if the pauses and resumes are balanced
                long pausingPointsSum = 0;
                for(long point : pausingPoints){
                    pausingPointsSum += point;
                }

                long resumingPointsSum = 0;
                for(long point : resumingPoints){
                    resumingPointsSum += point;
                }

                if(pausingPoints.length > resumingPoints.length){
                    // resumed
                    return pausingPointsSum - resumingPointsSum;
                }else{
                    // paused
                    // this needs to take the sum of pausing points minus the sum of resuming points plus the sum of pausing points plus the current system time
                    return pausingPointsSum - resumingPointsSum + System.currentTimeMillis() - startTime;
                }
            }else{
                return pausingPoints[0];
            }
        }else{
            return System.currentTimeMillis() - startTime;
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

    // Timing methods
    public void startTossupTimer(){
        tossupStartTime = getTimeMillis();
    }

    public void startBonusTimer(){
        bonusStartTime = getTimeMillis();
    }

    // summary statistics(This will contain methods that will get general summary statistics for the game)

    public int getTeamAScore(){
        return teamAPosScore + teamBNegScore;
    }

    public int getTeamBScore(){
        return teamBPosScore + teamANegScore;
    }

}
