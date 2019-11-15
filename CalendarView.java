import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.time.YearMonth;

class CalendarView extends JPanel{

    private static final String[] weekdayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    private static final String[] monthNames = {"January","February","March","April","May","June","July","August","September","October","November","December"};


    private CalendarDay[][] dateButtons = new CalendarDay[6][7];
    private Calendar date;
    private Label monthLabel;

    public CalendarView(){
        this(Calendar.getInstance());
    }

    public CalendarView(Calendar date){
        this.date = date;
        this.monthLabel = new Label("", Label.CENTER);

        setLayout(new GridBagLayout());
        setFont(getFont().deriveFont(18.0f));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        monthButtons(this.date, c);


        c.gridy = 1;
        for(int day=0;day<7;day++){
            c.gridx = day;
            add(new Label(weekdayNames[day], Label.CENTER),c);
        }



        c.gridy = 0;



        c.gridx = 1;
        Button previous = new Button("<");
        previous.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                date.add(Calendar.MONTH, -1);
                monthButtons(date, c);
            }
        });
        add(previous, c);


        c.gridx = 5;
        Button next = new Button(">");
        next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                date.add(Calendar.MONTH, 1);
                monthButtons(date, c);
            }
        });
        add(next, c);







        c.gridwidth = 3;
        c.gridx = 2;
        add(monthLabel,c);


    }



    public void monthButtons(Calendar date, GridBagConstraints c){
        for(CalendarDay[] buttons : this.dateButtons)
            for(CalendarDay button : buttons)
                if(button != null)
                    remove(button);
        int weeks = 6;
        int firstDay = CalendarView.firstWeekdayOfMonth(date);
        int monthLength = CalendarView.lengthOfMonth(date);


        c.gridwidth = 1;

        for(int week=0;week<weeks;week++){
            c.gridy = week+2;
            for(int day=0;day<7;day++){
                c.gridx = day;
                int dayOfMonth = (week*7+day-firstDay+1);
                CalendarDay cd = new CalendarDay(dayOfMonth, Color.WHITE);
                dateButtons[week][day] = cd;
                add(cd, c);
                if(week*7+day >= firstDay && dayOfMonth <= monthLength){
                }else{
                    cd.setBackground(Color.GRAY);
                    cd.setEnabled(false);
                    cd.setLabel("");
                }
            }
        }
        this.monthLabel.setText(monthNames[date.get(Calendar.MONTH)]+" "+date.get(Calendar.YEAR));
        this.highlightDate(Calendar.getInstance(), new Color(255,212,212));

        this.updateUI();
    }





    public void highlightDate(Calendar date, Color color){
        if(date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH)
        && date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR)){
            int dayOfMonth = date.get(Calendar.DAY_OF_MONTH) + CalendarView.firstWeekdayOfMonth(date) - 1;
            int week = dayOfMonth/7;
            int day = dayOfMonth%7;
            dateButtons[week][day].setBackground(color);
        }
    }









    public static int weeksToDisplay(Calendar date){
        Calendar c = (Calendar)date.clone();
        c.set(Calendar.DAY_OF_MONTH, 0);
        int weeks = (c.get(Calendar.DAY_OF_WEEK) + CalendarView.lengthOfMonth(date))/7;
        return weeks + 1;
    }
    public static int firstWeekdayOfMonth(Calendar date){
        Calendar c = (Calendar)date.clone();
        c.set(Calendar.DAY_OF_MONTH, 0);
        return c.get(Calendar.DAY_OF_WEEK)%7;
    }
    public static int lengthOfMonth(Calendar date){
        Calendar c = (Calendar)date.clone();
        YearMonth ym = YearMonth.of(c.get(Calendar.YEAR),c.get(Calendar.MONTH) + 1);
        return ym.lengthOfMonth();
    }

    public static void main(String[] args){
        JFrame window = new JFrame();
        CalendarView cv = new CalendarView();
        window.add(cv);
        window.setTitle("Calendar");
        window.setSize(400,250);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
