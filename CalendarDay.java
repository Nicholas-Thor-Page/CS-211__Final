import java.awt.Button;
import java.awt.Color;
public class CalendarDay extends Button{
    public CalendarDay(int day, Color color){
        super(day+"");
        super.setFocusable(false);
        super.setBackground(color);
    }
}
