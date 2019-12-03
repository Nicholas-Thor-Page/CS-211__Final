import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class TodoButton extends Button{
    private static final SimpleDateFormat df = new SimpleDateFormat("EEE, MMM dd, yyyy");

    TodoButton self = this;
    TodoItem item;

    public TodoButton(TodoItem item){
        super();
        this.item = item;
        //Change appearance based on completion of the item
        if(item.getCompleted()){
            super.setBackground(Color.LIGHT_GRAY);
            super.setLabel("(COMPLETED) "+item.getTitle());
        }else{
            super.setBackground(Color.WHITE);
            super.setLabel(item.getTitle());
        }

        //button press behaviour - opens a new window displaying information about that task
        super.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame window = new JFrame(item.getTitle());

                //set font size to 18
                window.getContentPane().setFont(window.getContentPane().getFont().deriveFont(18.0f));
                window.setLayout(new GridBagLayout());

                GridBagConstraints c = new GridBagConstraints();
                c.anchor = GridBagConstraints.PAGE_START;
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.gridwidth = 3;
                c.gridx = 0;
                //add text to the window
                c.weighty = 0.1;
                window.add(new Label(item.getTitle(), Label.CENTER), c);

                c.weighty = 0.8;
                window.add(new Label(item.getDescription(), Label.CENTER), c);

                c.gridwidth = 1;
                c.gridx = 1;
                c.weighty = 0.1;
                String dateString = "";
                if(item.getCalendar() != null)
                    dateString = df.format(item.getCalendar().getTime());
                window.add(new Label(dateString, Label.CENTER), c);

                //add buttons
                Button deleteButton = new Button("Delete Item");
                deleteButton.setBackground(new Color(255,127,127));
                Button completeButton = new Button();
                if(item.getCompleted()){
                    completeButton.setBackground(new Color(127,255,127));
                    completeButton.setLabel("Completed");
                }else{
                    completeButton.setBackground(new Color(127,127,255));
                    completeButton.setLabel("Not Completed");
                }






                deleteButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(JOptionPane.showConfirmDialog(window, "Are you sure you want to delete this item?") == JOptionPane.OK_OPTION){
                            Main.list.remove(item);
                            Main.save();
                            window.dispose();
                            self.getParent().remove(self);
                        }
                    }
                });



                completeButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //toggle completion
                        item.setCompleted(!item.getCompleted());
                        Main.save();

                        if(item.getCompleted()){
                            completeButton.setBackground(new Color(127,255,127));
                            completeButton.setLabel("Completed");
                            self.setBackground(Color.LIGHT_GRAY);
                            self.setLabel("(COMPLETED) "+item.getTitle());
                        }else{
                            completeButton.setBackground(new Color(127,127,255));
                            completeButton.setLabel("Not Completed");
                            self.setBackground(Color.WHITE);
                            self.setLabel(item.getTitle());
                        }
                    }
                });







                c.gridx = 0;
                window.add(completeButton, c);
                c.gridx = 2;
                window.add(deleteButton, c);

                //TODO: add buttons for deleting and finishing task

                window.setSize(800,600);
                window.setVisible(true);
            }
        });
    }
}
