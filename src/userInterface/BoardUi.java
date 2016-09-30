package userInterface;

import core.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardUi extends JPanel {
    
    private JButton[][] board;
    private BoardListener listener;
    
    public BoardUi() {
        initComponents();
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
                
                // Set a color if there was none.
                if(color == null) {
                    board[row][col].setIcon(new ImageIcon(getClass().getResource("../images/colorDark.png")));
                }
            }
        }
        
    }
}
