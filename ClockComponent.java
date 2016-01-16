import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockComponent extends JComponent
{
    public static final int DIAMETER = 300;
    public static final int frameW = (int) Math.round(DIAMETER * 1.75);
    public static final int frameH = (int) Math.round(DIAMETER * 1.75);
    public static Calendar calendar = new GregorianCalendar();

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(frameW/2,frameH/2);
        drawClockFace(g2);
        drawClockHands(g2);
    }

    public static void drawClockFace(Graphics2D g2){
        Ellipse2D.Double border = new Ellipse2D.Double(-DIAMETER/2,-DIAMETER/2,DIAMETER,DIAMETER);
        g2.setStroke(new BasicStroke(3));
        g2.draw(border);  

        double r1 = DIAMETER * 0.4;
        double r2 = DIAMETER * 0.45;
        int count=0;
        String hour = "";

        for(int theta = 0; theta < 360; theta=theta+6) {
            double thetaRadians = Math.toRadians(theta);
            double x1 = r1*Math.cos(thetaRadians);
            double y1 = -r1*Math.sin(thetaRadians);
            double x2 = r2*Math.cos(thetaRadians);
            double y2 = -r2*Math.sin(thetaRadians);
            if(theta%5==0){
                g2.setStroke(new BasicStroke(2));
                Line2D.Double tick = new Line2D.Double(x1,y1,x2,y2);
                g2.draw(tick);
            }
            if(theta%90==0 || theta==0) {
                g2.setStroke(new BasicStroke(2));
                switch(count){
                    case 0: 
                        hour="3";
                        break;
                    case 1: 
                        hour="12";
                        break;
                    case 2: 
                        hour="9";
                        break;
                    case 3: 
                        hour="6";
                        break;
                }
                if(hour.equals("12")) g2.drawString(hour,(int) Math.round(x2-6.7),(int) Math.round(y2+30));
                if(hour.equals("3")) g2.drawString(hour,(int) Math.round(x2-30),(int) Math.round(y2+5));
                if(hour.equals("6")) g2.drawString(hour,(int) Math.round(x2-3),(int) Math.round(y2-24));
                if(hour.equals("9")){ 
                    g2.drawString(hour,(int) Math.round(x2+25),(int) Math.round(y2+5));
                    String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                    g2.setColor(Color.ORANGE);
                    g2.drawString(day, (int) Math.round(x2+65), (int) Math.round(y2+5));
                    g2.setColor(Color.BLACK);
                }
                count++;
            } else {
                g2.setStroke(new BasicStroke(1));
            }
            Line2D.Double tick = new Line2D.Double(x1,y1,x2,y2);
            g2.draw(tick);
        }
    }

    public void drawClockHands(Graphics2D g2){
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        g2.setStroke(new BasicStroke(2));
        Ellipse2D.Double handOrigin = new Ellipse2D.Double(-3.75,-3.75,7.5,7.5);
        Line2D.Double minuteHand = new Line2D.Double(0,0,0,-(DIAMETER-203));
        Line2D.Double hourHand = new Line2D.Double(0,0,15,-(DIAMETER-240));
        Line2D.Double secondHand = new Line2D.Double(0,0,40,-(DIAMETER-194));
        g2.draw(minuteHand);
        g2.draw(hourHand);
        g2.draw(handOrigin);
        g2.setColor(Color.ORANGE);
        g2.setStroke(new BasicStroke(2));
        g2.draw(secondHand);
    }
}
