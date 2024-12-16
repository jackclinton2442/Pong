// Name:                  Date:

   import java.awt.*;
   import javax.swing.*;
   
    public class Bumper
   {
    //private fields, all ints, for a Bumper
    //hint: the "location" of the bumper begins at its top left corner.      
     private int myX;
    private int myY;
    private int myWidth;
    private int myHeight;
    private Color myColor;
   
   
     //constructors
       public Bumper()         //default constructor
      {
         myX = 200;
         myY = 200;
         myWidth = 5;
         myHeight = 50;
         myColor = Color.BLUE;
      }
       public Bumper(int x, int y, int w, int h, Color c)
      {
         myX = x;
         myY = y;
         myWidth = w;
         myHeight = h;
         myColor = c;
      }
      
     // accessor methods  (one for each field)
      public int getX(){
         return myX;
      }
      public int getY(){
         return myY;
      }
      public int getWidth(){
         return myWidth;
      }
      public int getHeight(){
         return myHeight;
      }
      public Color getColor(){
         return myColor;
      }
     // modifier methods  (one for each field)
      public void setX(int x){
         myX = x;
      }
      public void setY(int y){
         myY = y;
      }
     public void setWidth(int w){
         myWidth = w;
      } 
     public void setHeight(int h){
         myHeight = h;
      } 
     public void setColor(Color c){
      myColor = c;
     }
     //instance methods

      //chooses a random (x,y) location.  Bumper stays entirely in the window.
        public void jump(int rightEdge, int bottomEdge)
      {
         myX = (int)(Math.random() * ((double)(rightEdge) - (double)myWidth));
         myY = (int)(Math.random() * ((double)(bottomEdge) - (double)myHeight));
      }
      
      
       // draws a rectangular bumper on the buffer
       public void draw(Graphics myBuffer) 
      {
         myBuffer.setColor(getColor());
         myBuffer.fillRect(getX(), getY(), getWidth(), getHeight());
      }   
    // returns true if any part of the polkadot is inside the bumper
       public boolean inBumper(Polkadot dot)
      {
         for(int x = getX(); x <= getX() + getWidth(); x++)   //starts at upper left corner(x,y)
            for(int y = getY(); y <= getY() + getHeight(); y++)
               if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() ) //checks every point on the bumper
                  return true;            
         return false;
         
         
      }
         
         
        
      // returns distance between (x1, y1) and (x2, y2)
       private double distance(double x1, double y1, double x2, double y2)
      {
         return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
      }    
   }