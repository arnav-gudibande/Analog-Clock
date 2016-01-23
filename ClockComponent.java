import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.*;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ClockComponent extends JComponent
{
    public static final int DIAMETER = 300;//diameter of the clock
    public static final int frameW = (int) Math.round(DIAMETER * 1.75);//both the frameW and H of the Jframe
    public static final int frameH = (int) Math.round(DIAMETER * 1.75);
    public static Calendar calendar = new GregorianCalendar();//creates a new calendar object 

    public void paintComponent(Graphics g){//the paint component that creates a new Graphics2D JComponent and draws the clock hands and face on it
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(frameW/2,frameH/2);//translates the face of the graph to the origin, facilitates the drawing of the clock
        drawClockFace(g2);//draws the clock face and hands on the g2 object
        drawClockHands(g2);
    }

    public static void drawClockFace(Graphics2D g2){
        Ellipse2D.Double border = new Ellipse2D.Double(-DIAMETER/2,-DIAMETER/2,DIAMETER,DIAMETER);//draws the border of the clock, the center is the diameter/2 and its size is the diameter
        g2.setStroke(new BasicStroke(3));//emboldens the line
        g2.draw(border);//draws the circle on the Graphics2D object 

        double r1 = DIAMETER * 0.4;//sets r1 to 2/5 of the diameter - sets the hypotenuse value for the inner part of the tick mark
        double r2 = DIAMETER * 0.45;//sets r2 to 0.45 of the diameter - for the outer pt, as the function of the diameter
        int count=0;//count is 0
        String hour = "";//hour is initialized

        for(int theta = 0; theta < 360; theta=theta+6) {//for loop to go around the circle starting at 0 degrees and ending at 360 degrees, every 6 degrees

            double thetaRadians = Math.toRadians(theta);//converts the degrees to radians - invoking the static method used to convert theta to radians for calculation purposes
            double x1 = r1*Math.cos(thetaRadians);//calculates the x1 point, by multiplying r1 by the cosine of theta
            double y1 = -r1*Math.sin(thetaRadians);//calculates the y1 point, by multiplying r1 by the sin of theta
            double x2 = r2*Math.cos(thetaRadians);//calculates the x2 point, by multiplying r2 by the cosine of theta
            double y2 = -r2*Math.sin(thetaRadians);//calculates the y2 point, by multiplying r2 by the sin of theta
            if(theta%5==0){//if the theta is a multiple of five, then the tick represents one of the hours
                g2.setStroke(new BasicStroke(2));//if its an hour, then we embolden that specific tick
                Line2D.Double tick = new Line2D.Double(x1,y1,x2,y2);//draws the tick
                g2.draw(tick);//draws the line2d.double on the Graphical component
            }

            if(theta%90==0 || theta==0) {//if it is either 12,3,6 or 9, then embolden it and place a number by that tick
                g2.setStroke(new BasicStroke(2));//bold it 

                switch(count){//switch statement for the count
                    case 0: 
                    hour="3";//set the hour to 3
                    break;
                    case 1: 
                    hour="12";//set it to 12
                    break;
                    case 2: 
                    hour="9";//set it to 9
                    break;
                    case 3: 
                    hour="6";//set it to 6
                    break;
                }
                if(hour.equals("12")) g2.drawString(hour,(int) Math.round(x2-6.7),(int) Math.round(y2+30));//if its 12, then position the 12 below the tick
                if(hour.equals("3")) g2.drawString(hour,(int) Math.round(x2-30),(int) Math.round(y2+5));//if its 3, then position the 3 to the left of the tick
                if(hour.equals("6")) g2.drawString(hour,(int) Math.round(x2-3),(int) Math.round(y2-24));//if its 6, position the 6 above the tick
                if(hour.equals("9")){ //if its 9, position the 9 to the right of the tick
                    g2.drawString(hour,(int) Math.round(x2+25),(int) Math.round(y2+5));
                    String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));//also gets the day of the month and places it on the clock face
                    g2.setColor(Color.ORANGE);//sets the color to orange
                    g2.drawString(day, (int) Math.round(x2+65), (int) Math.round(y2+5));//draws the day of the month
                    g2.setColor(Color.BLACK);//sets the color to black
                }

                count++;//increases the count
            } else {
                g2.setStroke(new BasicStroke(1));//sets the stroke to regular
            }
            Line2D.Double tick = new Line2D.Double(x1,y1,x2,y2);//draws the rest of the minute ticks
            g2.draw(tick);
        }
    }

    public double getHourRadians(int hr, int min){//hour and minute goes in, returns the radian position of the hour hand
        double in = (double) min;
        double HrMin = hr+(in/60);//divides the min by 60 and adds it to the hour
        return Math.toRadians(30*(HrMin)-90);//converts it to degrees - then goes to radians
    }

    public double getMinRadians(int min, int sec){//minute goes in = returns the radian position of the minute hand
        double in = (double) sec;
        double SecMin = min+(in/60);
        return Math.toRadians(6*(SecMin)-90);//derived the formula from the table of values
    }

    public double getSecRadians(int sec){//second goes in = returns the radian posiino of the second hand
        return Math.toRadians(6*sec-90);//derived the formula from the table of values
    }

    public void drawClockHands(Graphics2D g2){

        int hour = calendar.get(Calendar.HOUR);//gets the hour, minute and second using the Calendar object
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        double r = DIAMETER * 0.235;//sets the size of the hour hand
        double rm = DIAMETER * 0.34;//sets the size of the minute hand
        double rs = DIAMETER * 0.3;//sets the size of the second hand

        double hRadians = getHourRadians(hour,minute);//gets the radian positon of the hour hand
        double mRadians = getMinRadians(minute, second);//gets the radian positon of the minute hand
        double sRadians = getSecRadians(second);//gets the radian positon of the second hand

        double xh = r*Math.cos(hRadians);//calculates the xh point, by multiplying r by the cosine of theta
        double yh = (-r*Math.sin(hRadians))*-1;//gets y value of hour hand
        double xm = rm*Math.cos(mRadians);//calculates the xm point, by multiplying rm by the cosine of theta
        double ym = (-rm*Math.sin(mRadians))*-1;//gets y value of the minute hand
        double xs = rs*Math.cos(sRadians);//calculates the xs point, by multiplying rs by the cosine of theta
        double ys = (-rs*Math.sin(sRadians))*-1;//gets y value of the second hand

        g2.setStroke(new BasicStroke(2));//thickens the line in the graphical component g2
        Ellipse2D.Double handOrigin = new Ellipse2D.Double(-3.75,-3.75,7.5,7.5);//sets the origin as a circle in the middle of the clock

        Line2D.Double minuteHand = new Line2D.Double(0,0,xm,ym);//minute hand is drawn, the xm and ym position is where the line connects
        Line2D.Double hourHand = new Line2D.Double(0,0,xh,yh);//hour hand is drawn, the xh and yh position is where the line connects
        Line2D.Double secondHand = new Line2D.Double(0,0,xs,ys);//second hand is drawn, the xs and ys position is where the line connects

        g2.draw(minuteHand);//draws the minute hand
        g2.draw(hourHand);//draws the hour hand
        g2.draw(handOrigin);//draws the origin of the clock
        g2.setColor(Color.ORANGE);//sets the color to orange
        g2.setStroke(new BasicStroke(2));
        g2.draw(secondHand);//draws the second hand
    }

}

