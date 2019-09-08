package com.company;

import GUI.Reader;
import GUI.Setup;
import GUI.Timer;

public class Main extends Thread{

    public static void main(String[] args) throws InterruptedException{
	// write your code here
        System.out.println("Started Science Bowl Program");

        // this is going to be the part of the code that will run the setup for this project

            // this will load the properties for this project

            // this is the code that will load the science fair database into a JSON file


            // this is the code that will run the setup GUI for the user to input the file data
            Setup s = new Setup();

        // this is kind of crude way to load the program but I think it will do fine

        while(!s.startGame){
            Thread.sleep(30);
        }
        System.out.println("Starting Game");

        // this will set up the Game Classes


        // this will set up the GUI
        Reader r = new Reader();
        Timer t = new Timer();







    }
}
