package com.mycompany.spacegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Fire {
    private int x;
    private int y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
public class Game extends JPanel implements KeyListener,ActionListener{
    Timer timer = new Timer(5,this);
    private int time_spent=0;
    private int balls_spent=0;
    private BufferedImage image;
    private ArrayList<Fire> fires = new ArrayList<Fire>();
    
    private int fireIsY = 1;
    
    private int ballX = 0;
    
    private int ballIsX = 2;
    
    private int spaceShipX = 0;
    
    private int IsSpace = 20; 
    
    public boolean controlIt() {
        for(Fire fire : fires)   {
          if(new Rectangle(fire.getX(), fire.getY(), 10, 20).intersects(new Rectangle(ballX,0,20,20)))  {
              return true;
          }
       }
        return false;
    }

    public Game() {
        try {
            image  = ImageIO.read(new FileImageInputStream(new File("SpaceShip.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.BLACK);
        
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        time_spent += 5;
        g.setColor(Color.red);
        g.fillOval(ballX, 0, 20, 20);
        g.drawImage(image, spaceShipX, 490, image.getWidth()/30, image.getHeight()/30, this);
        
        for(Fire fire : fires)   {
           if( fire.getY() <0)  {
               fires.remove(fire);
           }
       }
        g.setColor(Color.BLUE);
        for(Fire fire : fires)   {
           g.fillRect(fire.getX(), fire.getY(), 10, 20);
       }
        
        if(controlIt()) {
            timer.stop();
            String messsage = "You win\n" +
                              "Ball spent : " + balls_spent +
                              "\nTime spent : " + time_spent /1000.0 + " seconds";
            JOptionPane.showMessageDialog(this, messsage);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
         int c = e.getKeyCode();
        
        if(c == KeyEvent.VK_LEFT)   {
            if(spaceShipX <=0)  {
                spaceShipX = 0;
            }
            else {
                spaceShipX -= IsSpace;
            }
        }
        else if(c == KeyEvent.VK_RIGHT) {
            if(spaceShipX >= 710)   {
                spaceShipX = 710;
            }
            else    {
                spaceShipX += IsSpace;
            }
        }
        
        else if(c == KeyEvent.VK_SPACE) {
            fires.add(new Fire(spaceShipX+34,485));
            
            balls_spent++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       for(Fire fire : fires)   {
           fire.setY(fire.getY() - fireIsY);
       }
        ballX += ballIsX;
        
        if(ballX >= 770)    {
            ballIsX = -ballIsX;
        }
        if(ballX <= 0)  {
            ballIsX =- ballIsX;
        }
        
        repaint();
    }
    
}
