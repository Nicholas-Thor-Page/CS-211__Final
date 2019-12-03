import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.time.YearMonth;

/**
* CalendarView is a JPanel that displays a Calendar with a different color for
* the current date and days with TodoList items.
* The calendar only displays one month at a time, but there are buttons to change months.
*/
class CalendarView extends JPanel{

    private static final String[] weekdayNames = {" SUN "," MON "," TUE "," WED "," THU "," FRI "," SAT "};
    private static final String[] monthNames = {"January","February","March","April","May","June","July","August","September","October","November","December"};


    private CalendarDay[][] dateButtons = new CalendarDay[6][7];
    public Calendar date;
    private Label monthLabel;
    private TodoList list;
    /**
    creates a CalendarView with the current date/time
    */
    public CalendarView(TodoList list){
        this(Calendar.getInstance(), list);
    }

    public CalendarView(Calendar date, TodoList list){
        this.date = date;
        this.list = list;
        //monthLabel referrence used in both this constructor and monthButtons
        this.monthLabel = new Label("", Label.CENTER);
        //create gridbag layout
        setLayout(new GridBagLayout());
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        GridBagConstraints c = new GridBagConstraints();
        //fill the entire panel
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        //initialize buttons for each day of month
        c.gridwidth = 1;
        //add buttons
        for(int week=0;week<6;week++){
            c.gridy = week+2;
            for(int day=0;day<7;day++){
                c.gridx = day;
                CalendarDay cd = new CalendarDay(date, list, Color.WHITE);
                dateButtons[week][day] = cd;
                add(cd, c);
            }
        }
        //sets the correct date for each button
        monthButtons(this.date, c);

        //add labels for days of the week
        c.gridy = 1;
        for(int day=0;day<7;day++){
            c.gridx = day;
            add(new Label(weekdayNames[day], Label.CENTER),c);
        }




        c.gridy = 0;


        //navigation buttons
        //TODO: add functionality to input a desired month/date
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
        //get where month starts and how many days it has
        int firstDay = CalendarView.firstWeekdayOfMonth(date);
        int monthLength = CalendarView.lengthOfMonth(date);

        for(int week = 0;week<6;week++)
            for(int day = 0;day<7;day++){
                int dayOfMonth = week*7 + day - firstDay;
                if(dayOfMonth < 0 || dayOfMonth >= monthLength){
                    dateButtons[week][day].setEnabled(false);
                    dateButtons[week][day].setBackground(Color.GRAY);
                    dateButtons[week][day].setLabel(" ");
                }else{
                    Calendar newDate = (Calendar)date.clone();
                    newDate.set(Calendar.DAY_OF_MONTH, dayOfMonth + 1);
                    dateButtons[week][day].setEnabled(true);
                    dateButtons[week][day].setBackground(Color.WHITE);
                    dateButtons[week][day].changeDate(newDate);
                }
            }

        //update the monthLabel text
        this.monthLabel.setText(monthNames[date.get(Calendar.MONTH)]+" "+date.get(Calendar.YEAR));
        //highlight the current date
        this.highlightDate(Calendar.getInstance(), new Color(255,0,0));
        //highlight the dates with todoItems
        for(TodoItem item : list)
            this.highlightDate(item.getCalendar(), new Color(63,63,255));
        //push updates
        this.updateUI();
    }




    /**
    changes the color of the button corresponding to the specified date to the specified color
    @param date day to highlight
    @param color color to change the button to
    */
    public void highlightDate(Calendar date, Color color){
        if(date == null) return;
        //test to see if the displayed month has the date to be highlighted
        if(date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH)
        && date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR)){
            int dayOfMonth = date.get(Calendar.DAY_OF_MONTH) + CalendarView.firstWeekdayOfMonth(date) - 1;
            //get row and column of corresponding button
            int week = dayOfMonth/7;
            int day = dayOfMonth%7;

            Color oldColor = dateButtons[week][day].getBackground();
            int newRed = (color.getRed() + oldColor.getRed())/2;
            int newGreen = (color.getGreen() + oldColor.getGreen())/2;
            int newBlue = (color.getBlue() + oldColor.getBlue())/2;
            Color newColor = new Color(newRed, newGreen, newBlue);
            dateButtons[week][day].setBackground(newColor);
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
        CalendarView cv = new CalendarView(new TodoList());
        window.add(cv);
        window.setTitle("Calendar");
        window.setSize(400,250);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
