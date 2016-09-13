package core;

public class Board {
    
    private Disc[][] board;
    
    public Board() {
        initObjects();
    }

    public Disc[][] getBoard() {
        return board;
    }
    
    public void setBoard(Disc[][] board) {
        this.board = board;
    }
    
    private void initObjects() {
        int i, j;
        
        board = new Disc[8][8];
        
        // For each space in our 2D array, populate the space with a new
        // Disc object.
        for(i=0; i<8; i++) {
            for(j=0; j<8; j++) {
                board[i][j] = new Disc();
            }
        }
        
        // Set the initial state of the board according to the rules
        // of Othello.
        setBoardInitialState();
    }
    
    private void setBoardInitialState() {
        board[3][3].setDiscColor(Constants.LIGHT);
        board[3][4].setDiscColor(Constants.DARK);
        board[4][3].setDiscColor(Constants.DARK);
        board[4][4].setDiscColor(Constants.LIGHT);
    }
    
}
