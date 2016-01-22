import javax.swing.JFrame;
import java.util.Scanner;

public class MainEC
{

    public static void main(String[] args){
        JFrame clockFrame = new JFrame("Clock");//creates a new Jframe
        clockFrame.setSize(ClockComponent.frameW,ClockComponent.frameH);//sets the size of the Jframe
        ClockComponent cF = new ClockComponent();//creates a new clockcomponent object
        clockFrame.add(cF);//adds the clockcomponent to the Jframe
        clockFrame.setResizable(false);//cannot resize it
        clockFrame.setVisible(true);//sets it visible
    }
    
}
