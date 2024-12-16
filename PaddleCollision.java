public class PaddleCollision
   {
      private static double nearestX;	// used to approximate what point of the pad  
      private static double nearestY;  // a ball collided with
   
       public static void collide(Paddle pad, Ball ball)
      {
         // see if the ball hit the pad!
         if(pad.inPaddle(ball))
         {	   	
            // back the ball up until it is just outside the pad
            while(pad.inPaddle(ball))
            {
               ball.setX(ball.getX() - ball.getdx()/10.0);
               ball.setY(ball.getY() - ball.getdy()/10.0);
            }
            
            // find the point on the edge of the pad closest to the ball
            findImpactPoint(pad, ball);
         	
            double ux=nearestX-ball.getX();
            double uy=nearestY-ball.getY();
            double ur=Math.sqrt(ux*ux+uy*uy);
            ux/=ur;
            uy/=ur;
            int dx=(int)ball.getdx();
            int dy=(int)ball.getdy();
            double dot_1= ux*dx+uy*dy;
            double dot_2=-uy*dx+ux*dy;
            dot_1*=-1; // this is the actual "bounce"
            double[] d = new double[2];
            d[0]=dot_1*ux-dot_2*uy;      //vector math
            d[1]=dot_1*uy+dot_2*ux;      //vector math
            dx=(int)Math.round(d[0]);
            dy=(int)Math.round(d[1]);
            ball.setdx(dx);
            ball.setdy(dy);
         }
      }
      
       private static void findImpactPoint(Paddle pad, Ball ball)
      {
          // first assume the nw corner is closest
         nearestX = pad.getX();  
         nearestY = pad.getY();
         
         // now work around the edge of the pad looking for a closer point
         for (int x = pad.getX(); x <= pad.getX() + pad.getWidth(); x++)  // top & bottom edges
         {
            updateIfCloser(x, pad.getY(), ball);
            updateIfCloser(x, pad.getY() + pad.getHeight(), ball);
         }
         for (int y = pad.getY(); y <= pad.getY() + pad.getHeight(); y++)  // right & left edges
         {
            updateIfCloser(pad.getX(), y, ball);
            updateIfCloser(pad.getX() + pad.getWidth(), y, ball);
         }
      }
         
      // makes (x,y) the nearest point if it is closer to the ball
       private static void updateIfCloser(int x, int y, Ball b)
      {
         if(distance(x, y, b.getX(), b.getY()) < distance(nearestX, nearestY, b.getX(), b.getY()))
         {
            nearestX = x;
            nearestY = y;
         }
      }
          
      // returns distance between (x1, y1) and (x2, y2)
       private static double distance(double x1, double y1, double x2, double y2)
      {
         return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
      }	
   }