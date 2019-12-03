import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
//CalendarDay is a button that displays information about the events of that day when clicked.
//TODO: add functionality to open new window/panel when clicked
public class CalendarDay extends Button{
    private static SimpleDateFormat df = new SimpleDateFormat("EEE, MMM dd, yyyy");

    private CDActionListener listener;

    public CalendarDay(Calendar date, TodoList list, Color color){
        super.setFocusable(false);
        super.setBackground(color);
        this.listener = new CDActionListener(date, list);
        super.addActionListener(this.listener);
    }

    public void changeDate(Calendar date){
        //update the internal date and labelp
        this.listener.date = date;
        super.setLabel(date.get(Calendar.DAY_OF_MONTH)+"");
    }

    private class CDActionListener implements ActionListener{
        private Calendar date;
        private TodoList list;

        public CDActionListener(Calendar date, TodoList list){
            this.date = date;
            this.list = list;
        }

        public void actionPerformed(ActionEvent e){

            Calendar start = (Calendar)date.clone();
            start.set(Calendar.HOUR, 0);
            start.set(Calendar.MINUTE, 0);
            start.set(Calendar.SECOND, 0);
            start.set(Calendar.MILLISECOND, 0);


            Calendar end = (Calendar)start.clone();
            end.add(Calendar.DATE, 1);



            TodoList filteredList = list.filterByDate(start,end);
            if(filteredList.size() == 0) return;



            JFrame window = new JFrame();
            window.add(new ListView(filteredList));
            window.setTitle("To-Do Items for "+df.format(date.getTime()));
            window.setSize(800,600);
            window.revalidate();
            window.setVisible(true);
        }
    }
}
