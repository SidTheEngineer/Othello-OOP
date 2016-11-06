package core;

import java.awt.Color;

public class Player {
    
    private String name;
    private Color discColor;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getDiscColor() {
        return discColor;
    }

    public void setDiscColor(Color discColor) {
        this.discColor = discColor;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
