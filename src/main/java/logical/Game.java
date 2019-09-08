package logical;

public class Game { // this will store all of the relevant information to a game

    public Player[] aTeam;
    public Player[] bTeam;


    public Generator gen;

    public Game(Player[] aTeam, Player[] bTeam, String databasePath, String saveLocationPath, double targetDifficulty){
        // this saves the player array
        this.aTeam = aTeam;
        this.bTeam = bTeam;


        gen = new Generator(databasePath, targetDifficulty);
    }






    // summary statistics(This will contain methods that will get general summary statistics for the game)

    public int getTeamAScore(){
        return 0;

    }

    public int getTeamBScore(){
        return 0;
    }


}
