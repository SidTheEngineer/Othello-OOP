package userInterface;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUi extends JPanel {
    
    public GameUi() {
        // Creates components and puts them in the JPanel on
        // instantiation.
        initComponents();
    }
    
    private JLabel nameOne;
    private JLabel nameTwo;
    private JLabel scoreOne;
    private JLabel scoreTwo;
    
    private void initComponents() {
        
        // Set dimensions for JPanel.
        this.setPreferredSize(new Dimension(200, 100));
        this.setMinimumSize(new Dimension(200, 100));
        
        // Label components.
        nameOne = new JLabel("Player One");
        nameTwo = new JLabel("Player Two");
        scoreOne = new JLabel("0");
        scoreTwo = new JLabel("0");
       
        nameOne.setMinimumSize(new Dimension(100, 50));
        nameTwo.setMinimumSize(new Dimension(100, 50));
        scoreOne.setMinimumSize(new Dimension(100, 50));
        scoreTwo.setMinimumSize(new Dimension(100, 50));
        
        // JPanel uses FlowLayout layout manager, which adds components
        // left to right in a row, until it runs out of space and creates
        // a new row.
        this.add(nameOne);
        this.add(scoreOne);
        this.add(nameTwo);
        this.add(scoreTwo);
        
        
    }
}
