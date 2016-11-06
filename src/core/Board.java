package core;

import java.awt.Color;
import java.util.ArrayList;

public class Board {
    
    private Disc[][] board;
    private int darkCount;
    private int lightCount;
    private ArrayList<Player> players;
    
    public Board() {
        initObjects();
    }

    public Disc[][] getBoard() {
        return board;
    }
    
    public void setBoard(Disc[][] board) {
        this.board = board;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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
        board[3][3].setDiscColor(Constants.LIGHT);
        board[3][4].setDiscColor(Constants.DARK);
        board[4][3].setDiscColor(Constants.DARK);
        board[4][4].setDiscColor(Constants.LIGHT);
    }
    
    public int getDarkCount() {
        return darkCount;
    }
    
    public void setDarkCount(int darkCount) {
        this.darkCount = darkCount;
    }

    public int getLightCount() {
        return lightCount;
    }

    public void setLightCount(int lightCount) {
        this.lightCount = lightCount;
    }
    
    public void calculateScore() {
        int i, j;
        darkCount = 0;
        lightCount = 0;
        
        for(i=0; i<Constants.ROWS; i++ ) {
            for(j=0; j<Constants.COLUMNS; j++) {
                if(board[i][j].getDiscColor() == Constants.DARK) {
                    darkCount++;
                }
                else if(board[i][j].getDiscColor() == Constants.LIGHT) {
                    lightCount++;
                }
            }
        }
        
        // Players is an array of both player 1(0) and 2(1). Get
        // the corresponding player at each index and set its score.
        players.get(Constants.PLAYER_ONE).setScore(darkCount);
        players.get(Constants.PLAYER_TWO).setScore(lightCount);
    }
    
    public boolean checkUp(int row, int col, Color color, boolean flip) {
        
        int flippedSquares = 0;
        int checkRow = row-1;
        boolean sameColor = false;
        boolean isValid = false;
        
        while(checkRow >= 0 && !sameColor) {
            
            // If the space is empty, nothing can be flipped and is therefore
            // an invalid move.
            if(board[checkRow][col].getDiscColor() == Constants.EMPTY)
                return isValid;
            
            // If the piece in the square is not the color of the current player
            // moving, it can be flipped and is therefore a valid move.
            else if(board[checkRow][col].getDiscColor() != color)
                flippedSquares++;
            
            // Otherwise, the piece on the board is the same color as the player
            // that is currently moving.
            else {
                sameColor = true;
            }
            
            checkRow--;
        }
        
        if(sameColor && flippedSquares > 0) {
            board[row][col].setDiscColor(color);
            do {
                row--;
                flippedSquares--;
                board[row][col].setDiscColor(color);
            }while(flippedSquares > 0);
            
            isValid = true;
            calculateScore();
        }
        else
            isValid = false;
        
        return isValid;
    }
    
    public boolean checkUpLeft(int row, int col, Color color, boolean flip) {
        
        int flippedSquares = 0;
        int checkRow = row-1;
        int checkCol = col-1;
        boolean sameColor = false;
        boolean isValid = false;
        
        while(checkRow >= 0 && checkCol >= 0 && !sameColor) {
            
            // If the space is empty, nothing can be flipped and is therefore
            // an invalid move.
            if(board[checkRow][checkCol].getDiscColor() == Constants.EMPTY)
                return isValid;
            
            // If the piece in the square is not the color of the current player
            // moving, it can be flipped and is therefore a valid move.
            else if(board[checkRow][checkCol].getDiscColor() != color)
                flippedSquares++;
            
            // Otherwise, the piece on the board is the same color as the player
            // that is currently moving.
            else {
                sameColor = true;
            }
            
            checkRow--;
            checkCol--;
        }
        
        if(sameColor && flippedSquares > 0) {
            board[row][col].setDiscColor(color);
            do {
                row--;
                col--;
                flippedSquares--;
                board[row][col].setDiscColor(color);
            }while(flippedSquares > 0);
            
            isValid = true;
            calculateScore();
        }
        else
            isValid = false;
        
        
        
        return isValid;
    }
        
    public boolean checkLeft(int row, int col, Color color, boolean flip) {
        
        int flippedSquares = 0;
        int checkCol = col - 1;
        boolean sameColor = false;
        boolean isValid = false;
        
        while(checkCol >= 0 && !sameColor) {
            
            // If the space is empty, nothing can be flipped and is therefore
            // an invalid move.
            if(board[row][checkCol].getDiscColor() == Constants.EMPTY)
                return isValid;
            
            // If the piece in the square is not the color of the current player
            // moving, it can be flipped and is therefore a valid move.
            else if(board[row][checkCol].getDiscColor() != color)
                flippedSquares++;
            
            // Otherwise, the piece on the board is the same color as the player
            // that is currently moving.
            else {
                sameColor = true;
            }
            
            checkCol--;
        }
        
        if(sameColor && flippedSquares > 0) {
            board[row][col].setDiscColor(color);
            do {
                col--;
                flippedSquares--;
                board[row][col].setDiscColor(color);
            }while(flippedSquares > 0);
            
            isValid = true;
            calculateScore();
        }
        else
            isValid = false;
        
        return isValid;
    }
    
    public boolean checkDownLeft(int row, int col, Color color, boolean flip) {
        
        int flippedSquares = 0;
        int checkRow = row + 1;
        int checkCol = col - 1;
        boolean sameColor = false;
        boolean isValid = false;
        
        while((checkRow < Constants.ROWS && checkCol >= 0) && !sameColor) {
            
            // If the space is empty, nothing can be flipped and is therefore
            // an invalid move.
            if(board[checkRow][checkCol].getDiscColor() == Constants.EMPTY)
                return isValid;
            
            // If the piece in the square is not the color of the current player
            // moving, it can be flipped and is therefore a valid move.
            else if(board[checkRow][checkCol].getDiscColor() != color)
                flippedSquares++;
            
            // Otherwise, the piece on the board is the same color as the player
            // that is currently moving.
            else {
                sameColor = true;
            }
            
            checkRow++;
            checkCol--;
        }
        
        if(sameColor && flippedSquares > 0) {
            board[row][col].setDiscColor(color);
            do {
                row++;
                col--;
                flippedSquares--;
                board[row][col].setDiscColor(color);
            }while(flippedSquares > 0);
            
            isValid = true;
            calculateScore();
        }
        else
            isValid = false;
        
        return isValid;
    }
    
    public boolean checkDown(int row, int col, Color color, boolean flip) {
        
        int flippedSquares = 0;
        int checkRow = row + 1;
        boolean sameColor = false;
        boolean isValid = false;
        
        while(checkRow < Constants.ROWS && !sameColor) {
            
            // If the space is empty, nothing can be flipped and is therefore
            // an invalid move.
            if(board[checkRow][col].getDiscColor() == Constants.EMPTY)
                return isValid;
            
            // If the piece in the square is not the color of the current player
            // moving, it can be flipped and is therefore a valid move.
            else if(board[checkRow][col].getDiscColor() != color)
                flippedSquares++;
            
            // Otherwise, the piece on the board is the same color as the player
            // that is currently moving.
            else {
                sameColor = true;
            }
            
            checkRow++;
        }
        
        if(sameColor && flippedSquares > 0) {
            board[row][col].setDiscColor(color);
            do {
                row++;
                flippedSquares--;
                board[row][col].setDiscColor(color);
            }while(flippedSquares > 0);
            
            isValid = true;
            calculateScore();
        }
        else
            isValid = false;
        
        return isValid;
    }
    
    public boolean checkDownRight(int row, int col, Color color, boolean flip) {
        
        int flippedSquares = 0;
        int checkRow = row + 1;
        int checkCol = col + 1;
        boolean sameColor = false;
        boolean isValid = false;
        
        while((checkRow < Constants.ROWS && checkCol < Constants.COLUMNS) && !sameColor) {
            
            // If the space is empty, nothing can be flipped and is therefore
            // an invalid move.
            if(board[checkRow][checkCol].getDiscColor() == Constants.EMPTY)
                return isValid;
            
            // If the piece in the square is not the color of the current player
            // moving, it can be flipped and is therefore a valid move.
            else if(board[checkRow][checkCol].getDiscColor() != color)
                flippedSquares++;
            
            // Otherwise, the piece on the board is the same color as the player
            // that is currently moving.
            else {
                sameColor = true;
            }
            
            checkRow++;
            checkCol++;
        }
        
        if(sameColor && flippedSquares > 0) {
            board[row][col].setDiscColor(color);
            do {
                row++;
                col++;
                flippedSquares--;
                board[row][col].setDiscColor(color);
            }while(flippedSquares > 0);
            
            isValid = true;
            calculateScore();
        }
        else
            isValid = false;
        
        return isValid;
    }
    
    public boolean checkRight(int row, int col, Color color, boolean flip) {
        
        int flippedSquares = 0;
        int checkCol = col + 1;
        boolean sameColor = false;
        boolean isValid = false;
        
        while(checkCol < Constants.COLUMNS && !sameColor) {
            
            // If the space is empty, nothing can be flipped and is therefore
            // an invalid move.
            if(board[row][checkCol].getDiscColor() == Constants.EMPTY)
                return isValid;
            
            // If the piece in the square is not the color of the current player
            // moving, it can be flipped and is therefore a valid move.
            else if(board[row][checkCol].getDiscColor() != color)
                flippedSquares++;
            
            // Otherwise, the piece on the board is the same color as the player
            // that is currently moving.
            else {
                sameColor = true;
            }
            
            checkCol++;
        }
        
        if(sameColor && flippedSquares > 0) {
            board[row][col].setDiscColor(color);
            do {
                col++;
                flippedSquares--;
                board[row][col].setDiscColor(color);
            }while(flippedSquares > 0);
            
            isValid = true;
            calculateScore();
        }
        else
            isValid = false;
        
        return isValid;
    }
    
    public boolean checkUpRight(int row, int col, Color color, boolean flip) {
        
        int flippedSquares = 0;
        int checkRow = row - 1;
        int checkCol = col + 1;
        boolean sameColor = false;
        boolean isValid = false;
        
        while(checkRow >= 0 && checkCol < Constants.COLUMNS && !sameColor) {
            
            // If the space is empty, nothing can be flipped and is therefore
            // an invalid move.
            if(board[checkRow][checkCol].getDiscColor() == Constants.EMPTY)
                return isValid;
            
            // If the piece in the square is not the color of the current player
            // moving, it can be flipped and is therefore a valid move.
            else if(board[checkRow][checkCol].getDiscColor() != color)
                flippedSquares++;
            
            // Otherwise, the piece on the board is the same color as the player
            // that is currently moving.
            else {
                sameColor = true;
            }
            
            checkRow--;
            checkCol++;
        }
        
        if(sameColor && flippedSquares > 0) {
            board[row][col].setDiscColor(color);
            do {
                row--;
                col++;
                flippedSquares--;
                board[row][col].setDiscColor(color);
            }while(flippedSquares > 0);
            
            isValid = true;
            calculateScore();
        }
        else
            isValid = false;

        return isValid;
    }
    
    public boolean checkIfBoardFull() {
        return players.get(Constants.PLAYER_ONE).getScore() 
       + players.get(Constants.PLAYER_TWO).getScore()
       == 64;
    }
    
    public boolean gameOver(Color color) {
        
        int i, j;
        
        boolean gameOver = false;
            
        if(checkIfBoardFull()) {
            gameOver = true;
            return gameOver;
        }
        if(!hasMove(Constants.DARK) && !hasMove(Constants.LIGHT))
            gameOver = true;
        
        return gameOver;
    }
    
    public boolean hasMove(Color color) {
        
        boolean isMove = false;
        
        for(int row = 0; row<Constants.ROWS; row++) {
            for(int col = 0; col<Constants.COLUMNS; col++) {
                if(board[row][col].getDiscColor() == null && isMove == true) {
                    isMove = isValidMove(row, col, color, false);
                }
            }
        }
        
        return isMove;
    }
    
    public boolean isValidMove(int row, int col, Color color, boolean flip) {
        
        boolean isValid = false;
        
        // Check each direction.
        if(checkUp(row, col, color, false))
            isValid = true;
        else if(checkUpLeft(row, col, color, false))
            isValid = true;
        else if(checkLeft(row, col, color, false))
            isValid = true;
        else if(checkDownLeft(row, col, color, false))
            isValid = true;
        else if(checkDown(row, col, color, false))
            isValid = true;
        else if(checkDownRight(row, col, color, false))
            isValid = true;
        else if(checkRight(row, col, color, false))
            isValid = true;
        else if(checkUpRight(row, col, color, false))
            isValid = true;
        
        if(isValid)
            board[row][col].setDiscColor(color);
        
        //calculateScore();
        gameOver(color);
        
        return isValid;
        
    }
    
}
