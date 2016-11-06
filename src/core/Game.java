package core;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Game {
    
    // This is the constructor, it looks like a method declaration
    // but it uses the name of the class. When instantiating a new 
    // "Game" class, this constructor gets called, creating the new
    // object. 
    public Game() {
        initObjects(); // In this case, call initOjects on instantiation.
    }
    
    // ArrayLists are arrays that are resizeable, and include methods
    // that allow you to handle the size of the list inside.
    private ArrayList<Player> players;
    private Board board;
    private Player currentPlayer;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    private void initObjects() {
        board = new Board();
        createPlayers();
        board.setPlayers(players);
        //printPlayers();
        players.get(Constants.PLAYER_ONE).setScore(Constants.TWO);
        players.get(Constants.PLAYER_TWO).setScore(Constants.TWO);
        currentPlayer = players.get(Constants.PLAYER_ONE);
    }
    
    public void createPlayers() {
        
        // Because players is already defined as an ArrayList of Player objects,
        // we don't need to say it's a "new array list of player objects" again,
        // just use the diamond syntax and put the parentheses afterwords to
        // instantiate (JE7).
        players = new ArrayList<>();
        
        // Init each player of the game.
        for(int i=0; i<Constants.MAX_PLAYERS; i++) {
            String askName = JOptionPane.showInputDialog(null, "Enter player's name");
            Player player = new Player();
            player.setName(askName);
            
            if(i == 0)
                player.setDiscColor(Constants.DARK);
            else
                player.setDiscColor(Constants.LIGHT);
            
            players.add(player);
        }
    }
    
    public void printPlayers() {
        System.out.println("The game has the following players:");
        
        // Can use the forEach method to do something on each element
        // of a collection.
        players.forEach((player) -> {
            System.out.println(
                    "Player "
                    + player.getName()
                    + " is playing disc color "
                    + player.getDiscColor()
            );
        });
    }
     
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    public void calculateScore() {
       // board.calculateScore();
        
        //Update the score for each player.
        //players.get(Constants.PLAYER_ONE).setScore(board.getDarkCount());
        //players.get(Constants.PLAYER_TWO).setScore(board.getLightCount());
    }
}
