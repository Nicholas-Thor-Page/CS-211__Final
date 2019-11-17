import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
//CalendarDay is a button that displays information about the events of that day when clicked.
//TODO: add functionality to open new window/panel when clicked
public class CalendarDay extends Button{
    public CalendarDay(Calendar date, int day, Color color){
        super(day+"");
        super.setFocusable(false);
        super.setBackground(color);

        super.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame window = new JFrame();
                window.add(new Label("test"));
                window.revalidate();
                window.setVisible(true);
            }
        });
    }
}
