package userInterface;

import core.Game;
import userInterface.GameUi;
import userInterface.BoardUi;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class OthelloUi extends JFrame {
    
    private Game game;
    private GameUi gameUi;
    private BoardUi boardUi;
    
    public OthelloUi(Game game) {
        this.game = game;
        
        // Create components and put them in JFrame upon class
        // instantiation.
        initComponents();
    }
    
    private void initComponents() {
        this.setPreferredSize(new Dimension(600, 600));
        this.setMinimumSize(new Dimension(600, 600));
        
        // This is done to close the actual application once the 
        // UI is closed via X.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameUi = new GameUi();
        boardUi = new BoardUi();
        
        // By default, JFrame uses BorderLayout as its LayoutManager,
        // which is responsible for managing how UI components are placed
        // within framses/panels.
        this.add(gameUi, BorderLayout.NORTH);
        this.add(boardUi, BorderLayout.CENTER);
        this.setVisible(true);
    }
    
}
