package userInterface;

import core.Constants;
import core.Disc;
import core.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardUi extends JPanel {
    
    private JButton[][] board;
    private BoardListener listener;
    private Game game;
    private GameUi gameUi;
    
    public BoardUi(Game game, GameUi gameUi) {
        this.game = game;
        this.gameUi = gameUi;
        initComponents();
        listener.updateUi();
    }
    
    private void initComponents() {
        int i, j;
        
        this.setPreferredSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400, 400));
        
        // Default layout is FlowLayout, which is not what we want for
        // how the game board will look, so override the layout to GridLayout.
        this.setLayout(new GridLayout(Constants.ROWS, Constants.COLUMNS));
        
        board = new JButton[Constants.ROWS][Constants.COLUMNS];
        listener = new BoardListener();
        
        for(i = 0; i<Constants.ROWS; i++) {
            for(j = 0; j<Constants.COLUMNS; j++) {
                board[i][j] = new JButton();
                
                // These properties are how we'll know where the target of an
                // event is happening, as well as what hte color is for that
                // particular spot in the grid.
                board[i][j].putClientProperty("row", i);
                board[i][j].putClientProperty("col", j);
                board[i][j].putClientProperty("color", Constants.EMPTY);
                
                board[i][j].setBackground(Color.WHITE);
                
                // Listens for actions (such as a click).
                board[i][j].addActionListener(listener);
                
                // Add the board to the JPanel.
                this.add(board[i][j]);
                
            }
        }
        
    }
    
    private class BoardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JButton) {
                
                // Explicit type cast: changing the type of what is to be 
                // assigned to a var into an explicitly chosen type.
                JButton button = (JButton)e.getSource();
                int row = (int)button.getClientProperty("row");
                int col = (int)button.getClientProperty("col");
                Color color = (Color)button.getClientProperty("color");
                
                // If the move is valid, make the move and switch to the other
                // player's turn.
                if(isValidMove(row, col, game.getCurrentPlayer().getDiscColor())) {
                    updateUi();
                    changePlayer();
                }
                else
                    JOptionPane.showMessageDialog(
                            button,
                            "Move is not valid, select another space."
                    );
            }
        }
        
        public void updateUi() {
            Disc[][] discs = game.getBoard().getBoard();
            ImageIcon disc = null;
            
            for(int row = 0; row<Constants.ROWS; row++) {
                for(int col = 0; col<Constants.COLUMNS; col++) {
                    if(discs[row][col].getDiscColor() == Constants.DARK) {
                        disc = new ImageIcon(getClass().getResource("../images/colorDark.png"));
                        board[row][col].setIcon(disc);
                        board[row][col].putClientProperty("color", Constants.DARK);
                    }
                    else if(discs[row][col].getDiscColor() == Constants.LIGHT) {
                        disc = new ImageIcon(getClass().getResource("../images/colorLight.png"));
                        board[row][col].setIcon(disc);
                        board[row][col].putClientProperty("color", Constants.LIGHT);
                    }
                }
            }
            
            gameUi.getScoreOne().setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_ONE).getScore()));
            gameUi.getScoreTwo().setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_TWO).getScore()));
        }
        
        public boolean isValidMove(int row, int col, Color color) {
            
            boolean isValid = false;
            
            if(board[row][col].getClientProperty("color") != Constants.EMPTY) {
                isValid = false;
            }
            else if(game.getBoard().isValidMove(row, col, color, false)) {
                isValid = true;
            }
            
            return isValid;
        }
        
        public void changePlayer() {
            if(game.getCurrentPlayer() == game.getPlayers().get(Constants.PLAYER_ONE))
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_TWO));
            else
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_ONE)); 
        }
        
    }
}
