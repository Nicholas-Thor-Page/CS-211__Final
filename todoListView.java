import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

public class TodoListView extends JPanel{
    public TodoListView(TodoList list){
        this(Calendar.getInstance(), list);
    }
    public TodoListView(Calendar date, TodoList list){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setFont(getFont().deriveFont(18.0f));



        Calendar min = Calendar.getInstance();
        min.setTime(new Date(Long.MIN_VALUE));

        Calendar today = (Calendar)date.clone();
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        Calendar tomorrow = (Calendar)today.clone();
        tomorrow.add(Calendar.DATE, 1);

        Calendar afterTomorrow = (Calendar)tomorrow.clone();
        afterTomorrow.add(Calendar.DATE, 1);

        Calendar nextWeek = (Calendar)today.clone();
        nextWeek.add(Calendar.DATE, 7);

        Calendar max = Calendar.getInstance();
        max.setTime(new Date(Long.MAX_VALUE));





        ListView allView = new ListView(list);
        ListView pastView = new ListView(list.filterByDate(min,today));
        ListView todayView = new ListView(list.filterByDate(today,tomorrow));
        ListView tomorrowView = new ListView(list.filterByDate(tomorrow,afterTomorrow));
        ListView weekView = new ListView(list.filterByDate(today,nextWeek));
        ListView futureView = new ListView(list.filterByDate(today,max));




        add(new Label("All"));
        add(allView);
        add(new Label("Past"));
        add(pastView);
        add(new Label("Today"));
        add(todayView);
        add(new Label("Tomorrow"));
        add(tomorrowView);
        add(new Label("This Week"));
        add(weekView);
        add(new Label("Future"));
        add(futureView);




        Button addNewItem = new Button("Add New Item");
        addNewItem.setBackground(new Color(127,255,127));

        addNewItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame window = new NewItemMenu();
            }
        });

        add(addNewItem);

    }
}
