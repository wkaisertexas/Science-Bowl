package com.company;

import GUI.Reader;
import GUI.Setup;
import GUI.Timer;

import logical.Game;

public class Main{
    public Setup s;
    public Game g;

    public Reader r;
    public Timer t;


    private Main(){
        // this launches the setup file
        System.out.println("Started Science Bowl Program");
        s = new Setup(this);
    }

    public void startGame(){
        System.out.println("Starting Game");
        g = new Game(s.getTeamA(), s.getTeamB(), s.getDatabasePath(), s.getSaveLocation(), s.getTargetAccuracy(), this);

    }

    public void gameComplete(){
        // this is going to re-initiate the setup GUI and dispose of the game files
        g = null; // this kills the game class (We have to make sure all of the GUI windows attached to the game are gone as well)

        // this will reopen the setup
        s.newGame();
    }





    public static void main(String[] args) throws InterruptedException{ Main m = new Main();}
}
