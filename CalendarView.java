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
    /**
    creates a CalendarView with the current date/time
    */
    public CalendarView(){
        this(Calendar.getInstance());
    }

    public CalendarView(Calendar date){
        this.date = date;
        //monthLabel referrence used in both this constructor and monthButtons
        this.monthLabel = new Label("", Label.CENTER);
        //create gridbag layout
        setLayout(new GridBagLayout());
        setFont(getFont().deriveFont(18.0f));
        GridBagConstraints c = new GridBagConstraints();
        //fill the entire panel
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        //add the buttons for each day of the month
        monthButtons(this.date, c);

        //add labels for days of the week
        c.gridy = 1;
        for(int day=0;day<7;day++){
            c.gridx = day;
            add(new Label(weekdayNames[day], Label.CENTER),c);
        }




        c.gridy = 0;


        //navigation buttons
        c.gridx = 1;
        Button previous = new Button("<");
        previous.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //decrease month by 1 and update buttons
                date.add(Calendar.MONTH, -1);
                monthButtons(date, c);
            }
        });
        add(previous, c);


        c.gridx = 5;
        Button next = new Button(">");
        next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //increase month by 1 and update buttons
                date.add(Calendar.MONTH, 1);
                monthButtons(date, c);
            }
        });
        add(next, c);






        //add the month label
        c.gridwidth = 3;
        c.gridx = 2;
        add(monthLabel,c);


    }



    private void monthButtons(Calendar date, GridBagConstraints c){
        //delete previous buttons if any
        for(CalendarDay[] buttons : this.dateButtons)
            for(CalendarDay button : buttons)
                if(button != null)
                    remove(button);
        int weeks = 6;
        //get where month starts and how many days it has
        int firstDay = CalendarView.firstWeekdayOfMonth(date);
        int monthLength = CalendarView.lengthOfMonth(date);


        c.gridwidth = 1;
        //add buttons
        for(int week=0;week<weeks;week++){
            c.gridy = week+2;
            for(int day=0;day<7;day++){
                c.gridx = day;
                //get numerical day of month
                int dayOfMonth = (week*7+day-firstDay+1);
                CalendarDay cd = new CalendarDay(dayOfMonth, Color.WHITE);
                //add button reference for later deletion
                dateButtons[week][day] = cd;
                add(cd, c);
                //TODO: remove else
                //if the day is not in the month, use a disabled greyed-out button instead
                if(week*7+day >= firstDay && dayOfMonth <= monthLength){
                }else{
                    cd.setBackground(Color.GRAY);
                    cd.setEnabled(false);
                    cd.setLabel("");
                }
            }
        }
        //update the monthLabel text
        this.monthLabel.setText(monthNames[date.get(Calendar.MONTH)]+" "+date.get(Calendar.YEAR));
        //highlight the current date
        this.highlightDate(Calendar.getInstance(), new Color(255,212,212));
        //push updates
        this.updateUI();
    }




    /**
    changes the color of the button corresponding to the specified date to the specified color
    @param date day to highlight
    @param color color to change the button to
    */
    public void highlightDate(Calendar date, Color color){
        //test to see if the displayed month has the date to be highlighted
        if(date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH)
        && date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR)){
            int dayOfMonth = date.get(Calendar.DAY_OF_MONTH) + CalendarView.firstWeekdayOfMonth(date) - 1;
            //get row and column of corresponding button
            int week = dayOfMonth/7;
            int day = dayOfMonth%7;
            dateButtons[week][day].setBackground(color);
        }
    }









    public static int firstWeekdayOfMonth(Calendar date){
        //create a copy of the calendar instance
        Calendar c = (Calendar)date.clone();
        //set the day to the first day of the month, and get what day of the week it is.
        c.set(Calendar.DAY_OF_MONTH, 0);
        return c.get(Calendar.DAY_OF_WEEK)%7;
    }
    /**
    @see YearMonth.of(int year, int month)
    @see YearMonth.lengthOfMonth
    */
    public static int lengthOfMonth(Calendar date){
        Calendar c = (Calendar)date.clone();
        YearMonth ym = YearMonth.of(c.get(Calendar.YEAR),c.get(Calendar.MONTH) + 1);
        return ym.lengthOfMonth();
    }






    //TODO: for testing
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
