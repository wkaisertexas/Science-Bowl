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

    public Game(Player[] aTeam, Player[] bTeam, String databasePath, String saveLocationPath, double targetDifficulty, Main m){
        // this saves the player array
        this.aTeam = aTeam;
        this.bTeam = bTeam;


        gen = new Generator(databasePath, targetDifficulty);

        this.r = new Reader(aTeam, bTeam);
        this.t = new Timer();

        this.m = m;
    }

    public void startGame(){




    }



    public void endGame(){


        // this tells the main class that the game has ended
        m.gameComplete();
    }




    // summary statistics(This will contain methods that will get general summary statistics for the game)

    public int getTeamAScore(){
        return 0;

    }

    public int getTeamBScore(){
        return 0;
    }


}
