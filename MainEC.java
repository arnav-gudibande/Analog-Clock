import javax.swing.JFrame;
import java.util.Scanner;

public class MainEC
{
    public static void main(String[] args){
        JFrame clockFrame = new JFrame("Clock");
        clockFrame.setSize(ClockComponent.frameW,ClockComponent.frameH);
        ClockComponent cF = new ClockComponent();
        clockFrame.add(cF);
        clockFrame.setResizable(false);
        clockFrame.setVisible(true);
    }
}
