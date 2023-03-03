package com.mycompany.spacegame;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameScreen extends JFrame{

    public GameScreen(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args)  {
        GameScreen screen = new GameScreen("Space Game");
        //editing JFrame  
        screen.setResizable(false);
        screen.setFocusable(false);
        screen.setSize(800,600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //keyboard operations
        Game game = new Game();
        game.requestFocus(); 
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        //adding JPanel to screen
        screen.add(game);
        screen.setVisible(true);
        
    }
}
 