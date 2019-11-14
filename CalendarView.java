import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.time.YearMonth;

class CalendarView extends JPanel{

    private static final String[] weekdayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    private static final String[] monthNames = {"January","February","March","April","May","June","July","August","September","October","November","December"};


    private CalendarDay[] dateButtons;
    private Calendar date;

    public CalendarView(Calendar date){
        this.date = date;

        int weeks = 6;
        int firstDay = CalendarView.firstWeekdayOfMonth(date);
        int monthLength = CalendarView.lengthOfMonth(date);


        this.dateButtons = new CalendarDay[weeks*7];



        setLayout(new GridBagLayout());
        setFont(getFont().deriveFont(18.0f));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;



        for(int week=0;week<weeks;week++){
            c.gridy = week+2;
            for(int day=0;day<7;day++){
                c.gridx = day;
                int dayOfMonth = (week*7+day-firstDay+1);
                if(week*7+day >= firstDay && dayOfMonth <= monthLength){
                    CalendarDay cd = new CalendarDay(dayOfMonth, Color.WHITE);
                    dateButtons[dayOfMonth] = cd;
                    add(cd, c);
                }else{
                    Button b = new Button();
                    b.setFocusable(false);
                    b.setBackground(Color.GRAY);
                    b.setEnabled(false);
                    add(b,c);
                }
            }
        }


        c.gridy = 1;
        for(int day=0;day<7;day++){
            c.gridx = day;
            add(new Label(weekdayNames[day], Label.CENTER),c);
        }



        c.gridy = 0;

        c.gridx = 1;
        add(new Button("<"), c);
        c.gridx = 5;
        add(new Button(">"), c);

        c.gridwidth = 3;
        c.gridx = 2;
        add(new Label(monthNames[date.get(Calendar.MONTH)]+" "+date.get(Calendar.YEAR), Label.CENTER),c);



        this.highlightDate(Calendar.getInstance(), new Color(255,212,212));
    }









    public void highlightDate(Calendar date, Color color){
        if(date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH)
        && date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR)){
            dateButtons[date.get(Calendar.DAY_OF_MONTH)].setBackground(color);
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
        YearMonth ym = YearMonth.of(c.get(Calendar.YEAR),c.get(Calendar.MONTH) - 1);
        return ym.lengthOfMonth();
    }

    public static void main(String[] args){
        JFrame window = new JFrame();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH,0);
        CalendarView cv = new CalendarView(c);
        window.add(cv);
        window.setTitle("Calendar");
        window.setSize(400,250);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
