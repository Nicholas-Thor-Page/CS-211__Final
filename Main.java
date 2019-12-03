import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main{
    static TodoList list = new TodoList();
    static CalendarView cv;
    static TodoListView lv;
    static JFrame window;
    public static void main(String[] args){
        try{
            list = FileReader.readData("list.tdl");
        }catch(IOException e){
            e.printStackTrace();
        }




        window = new JFrame();
        cv = new CalendarView(list);
        lv = new TodoListView(list);
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 1;
        c.ipadx = 20;
        c.ipady = 40;
        window.add(cv,c);
        window.add(Box.createHorizontalGlue(), c);
        c.weightx = 0.2;
        window.add(lv,c);





        window.setTitle("To-do List Manager");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void save(){
        try{
            FileReader.storeData(list, "list.tdl");
        }catch(Exception e){
            e.printStackTrace();
        }
        window.dispose();
        main(new String[0]);
    }
}
