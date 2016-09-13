package core;

import java.awt.Color;

public class Constants {
    // public: visible, can be accessed in other objects of other types.
    //
    // static: belongs to the class itself, not a particular instance of
    //         the class, so we can use these without instantiation
    public static Color DARK = Color.BLACK;
    public static Color LIGHT = Color.WHITE;
    public static int PLAYER_ONE = 0;
    public static int PLAYER_TWO = 1;
    public static int ROWS = 8;
    public static int COLUMNS = 8;
    public static int MAX_PLAYERS = 2;
}
