import javax.swing.JComponent;//importing the neccessary componnents
import java.awt.*;
import java.awt.geom.*;

public class ClockComponent extends JComponent
{
    public static final int DIAMETER = 400;
    
    public ClockComponent(){
        //
    }
    
    public void paintComponent(Graphics g){
       Graphics2D g2 = (Graphics2D) g;
       g2.translate(350,350);
       //draw the clock face
       //draw the clock hands in current time
       drawClockHands(g2);
    }
    
    public static void drawClockFace(Graphics2D g2){
       //draw the outside border
       //draw tick marks along inner perimeter
       //draw the numbers in the proper position
       Ellipse2D.Double border = new Ellipse2D.Double(0,0,DIAMETER,DIAMETER);
       g2.setStroke(new BasicStroke(2));
       g2.draw(border);  
    
       /*
        * write a for loop that enumerates around the circle
        * at every interval, draw a tick line that is perpendicular to the edge of the circle
        * stop when you go fully around the circle
        */
    
    }
    
    public void drawClockHands(Graphics2D g2){
      
        //this is the method for animating the clock hands
    
    
    } 
}
