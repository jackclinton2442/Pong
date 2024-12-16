import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class PongPanel extends JPanel {
   private static final int FRAME = 400;
   private static final Color BACKGROUND = new Color(204, 204, 204);
   private static final Color BALL_COLOR = Color.BLACK;
   private static final Color PRIZE_COLOR = Color.RED;
   private static final Color BUMPER_COLOR = Color.BLUE;


   private BufferedImage myImage;
   private Graphics myBuffer;
   private Ball ball;
   private Polkadot prize;
   private Bumper bumper;
   private int hits;
   private Timer timer1;
   private Timer timer2;
   private Paddle paddle1;
   private Paddle paddle2;
   private int score1 = 0; // Player 1 score
   private int score2 = 0; // Player 2 score

   public PongPanel() 
   
   
   {
      
      
      
      myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
   
      // Create ball and set initial position
      ball = new Ball();
      paddle1 = new Paddle( 15,  200,  5 ,  100, Color.RED);
      paddle2 = new Paddle( 385,  200,  5 ,  100, Color.RED);
      // Create bumper
      bumper = new Bumper();
      
      
      addKeyListener(new Key());
      timer2 = new Timer(5, new Listener());
      timer2.start();
      setFocusable(true);
   
      // Timer to move bumper every 3 seconds
      timer1 = new Timer(3000, 
         new ActionListener() 
         {
            public void actionPerformed(ActionEvent e) 
            {
               bumper.jump(400,360); // Set new random position in the middle area every 3 seconds
               repaint(); // Ensure the bumper is redrawn
            }
         });
      timer1.start();
   
   
   
      // Timer for game upaddle2ate
      Timer gameTimer = new Timer(5, new Listener());
      gameTimer.start();
   }
   
   private class Key extends KeyAdapter {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_UP)
         {
            if(paddle2.getY()-20>0){
               paddle2.setY(paddle2.getY()-20);
            }
         }
         if(e.getKeyCode()==KeyEvent.VK_DOWN)
         {
            if(paddle2.getY()+120<FRAME)
            {
               paddle2.setY(paddle2.getY()+20);
            }
         }
         
         if(e.getKeyCode()==KeyEvent.VK_W)
         {
            if(paddle1.getY()-20>0){
               paddle1.setY(paddle1.getY()-20);
            }
         }
         if(e.getKeyCode()==KeyEvent.VK_S)
         {
            if(paddle1.getY()+120<FRAME)
            {
               paddle1.setY(paddle1.getY()+20);
            }
         }
      
      
      }
   }
      
   

 

   public void paintComponent(Graphics g) 
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      g.setColor(Color.BLACK);
      g.setFont(new Font("Arial", Font.BOLD, 18));
      g.drawString("Player 1: " + score1, 15, 30);
      g.drawString("Player 2: " + score2, FRAME - 150, 30);
   }

   private class Listener implements ActionListener 
   {
      public void actionPerformed(ActionEvent e)
      {
        // Clear buffer and move ball
         myBuffer.setColor(BACKGROUND);
         myBuffer.fillRect(0, 0, FRAME, FRAME);
        
         ball.move(FRAME, FRAME);  // Move the ball
        
        // Check for paddle and bumper collisions
         PaddleCollision.collide(paddle1, ball);
         PaddleCollision.collide(paddle2, ball);
         BumperCollision.collide(bumper, ball);
        
        // Check if the ball crosses the left or right walls and update the score
         while (ball.getX() <= 0 + ball.getRadius()) 
         {
            score2++;  // Player 2 scores
            ball.setX(FRAME / 2); // Reset ball to the center horizontally
            ball.setY(FRAME / 2); // Reset ball to the center vertically
            ball.setdx(1);  // Reset horizontal speed
            if (Math.random() < 0.5) // Random vertical direction
            {
               ball.setdy(1);
            } else 
            {
               ball.setdy(-1);
            }
         // Random vertical direction
         } 
         while (ball.getX() >= FRAME - ball.getRadius()) 
         {
            score1++;  // Player 1 scores
            ball.setX(FRAME / 2); // Reset ball to the center horizontally
            ball.setY(FRAME / 2); // Reset ball to the center vertically
            ball.setdx(-1);  // Reset horizontal speed
             if (Math.random() < 0.5) // Random vertical direction

            {
               ball.setdy(1);
            } else 
            {
               ball.setdy(-1);
            } 
         }        
        // Draw all game elements
         ball.draw(myBuffer);
         bumper.draw(myBuffer);
         paddle1.draw(myBuffer);
         paddle2.draw(myBuffer);
        
         repaint(); // Redraw the panel after all updates
      }
   }
   
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
      
   }
   
   
}



