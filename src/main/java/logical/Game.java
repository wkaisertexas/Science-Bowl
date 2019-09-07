package logical;

public class Game { // this will store all of the relevant information to a game

    public Player[] aTeam;
    public Player[] bTeam;

    public Game(Player[] aTeam, Player[] bTeam){
        // this saves the player array
        this.aTeam = aTeam;
        this.bTeam = bTeam;

    }






    // summary statistics(This will contain methods that will get general summary statistics for the game)

    public int getTeamAScore(){
        return 0;

    }

    public int getTeamBScore(){
        return 0;
    }


}
