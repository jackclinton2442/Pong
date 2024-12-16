import java.awt.*;

public class Ball extends Polkadot
{
   private double dx;       // pixels to move each time step() is called.
   private double dy;
// constructors
   public Ball()         //default constructor
   {
      super(200, 200, 60, Color.BLACK);
      dx = 1;          // to move vertically
      dy = 1;          // to move sideways
   }
   public Ball(double x, double y, double dia, Color c)
   {
      super(x, y, dia, c);
      dx = 1;
      dy = 1;
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

  //accessor methods
   public double getdx()
   {
      return dx;
   }
   public double getdy()
   {
      return dy;
   }

 //instance methods
   public void move(double rightEdge, double bottomEdge)
   {
      setX(getX()+ dx);      // move horizontally
   
      if(getX() >= rightEdge - getRadius())  //hit right edge
      {
         setX(rightEdge - getRadius());
         dx = dx * -1;
      }
   
      if(getX() <= getRadius())  //hit left edge
      {
         setX(getRadius());
         dx = dx * -1;
      }
   
      setY(getY() + dy);                  // move vertically
   
      if(getY() >= bottomEdge - getRadius()){ //hit bottom edge
         setY(bottomEdge - getRadius());
         dy = dy * -1;
      }
   
      if(getY() <= getRadius()){  //hit top edge
         setY(getRadius());
         dy = dy * -1;
      }
   
   
   }
}