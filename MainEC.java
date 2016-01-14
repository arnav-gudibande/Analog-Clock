public class MainEC
{
    public static void main(Strings[] args){
        JFrame clockFrame = new JFrame("Clock");
        clockFrame.setSize(clockComponent.DIAMETER*1.75, clockComponent.DIAMETER*1.75);
        clockComponent c = new ClockComponent();
        clockFrame.add(c);
        clockFrame.setVisible(true);
    }
}
