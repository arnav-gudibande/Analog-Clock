import javax.swing.JFrame;//imports the jframe components
import java.util.Scanner;//imports the scanner components
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class MainEC
{
    public static void main(String[] args){
        JFrame clockFrame = new JFrame("Clock");
        int size = ClockComponent.DIAMETER * (int) 1.75;
        clockFrame.setSize(size,size);
        ClockComponent cF = new ClockComponent(50);
        clockFrame.add(cF);
        clockFrame.setVisible(true);
        getTime();
    }
    
    public static void getTime(){
        Calendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        System.out.print(hour);
        System.out.print(minute);
        System.out.print(second);
        System.out.println("");
    }
}
