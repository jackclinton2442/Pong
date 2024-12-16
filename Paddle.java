 // Date:
import java.awt.*;
public class Paddle
{
   private double dx;       // pixels to move each time step() is called.
   private double dy;
   private int myX;
   private int myY;
   private int myWidth;
   private int myHeight;
   private Color myColor;

    // constructors
   public Paddle()         //default constructor
   {
      super();
      myX = 0;
      myY = 0;
      myWidth = 5;
      myHeight = 75;
      myColor = Color.BLACK;
           
   }
   public Paddle(int x, int y, int w, int h, Color c)
   {
      myX = x;
      myY = y;
      myWidth = w;
      myHeight = h;
      myColor = c;
   }
      
     //modifier methods 
   public void setdx(double x)        
   {
      dx = x;
   }
   public void setdy(double y)
   {
      dy = y;
   }
   public int getX()
   {
      return myX;
   }
   public int getY()
   {
      return myY;
   }
   public int getWidth()
   {
      return myWidth;
   }
   public int getHeight()
   {
      return myHeight;
   }
   public Color getColor()
   {
      return myColor;
   }

      
      //accessor methods
   public double getdx()             
   {
      return dx;
   }
   public double getdy()
   {
      return dy;
   }
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
    public void draw(Graphics myBuffer) 
      {
         myBuffer.setColor(getColor());
         myBuffer.fillRect(getX(), getY(), getWidth(), getHeight());
      }   
  public boolean inPaddle(Polkadot dot)
      {
         for(int x = getX(); x <= getX() + getWidth(); x++)   //starts at upper left corner(x,y)
            for(int y = getY(); y <= getY() + getHeight(); y++)
               if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() ) //checks every point on the bumper
                  return true;            
         return false;
         }
         
          private double distance(double x1, double y1, double x2, double y2)
      {
         return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
      }	

         
         
      }
      

 

   
