import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class NewItemMenu extends JFrame{

    private NewItemMenu self;

    public NewItemMenu(){
        super("New Item");
        JPanel panel = new JPanel();
        self = this;
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        TextField titleField = new TextField();
        TextField descriptionField = new TextField();



        Calendar now = Calendar.getInstance();


        Choice day = new Choice();
        for(int d=1;d<=31;d++){
            day.add(d+"");
        }
        day.select(now.get(Calendar.DATE) - 1);


        Choice month = new Choice();
        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");
        month.select(now.get(Calendar.MONTH));

        Choice year = new Choice();
        for(int y=1970;y<=2999;y++){
            year.add(y+"");
        }
        year.select(now.get(Calendar.YEAR) - 1970);


        day.setEnabled(false);
        month.setEnabled(false);
        year.setEnabled(false);




        Checkbox dateCheck = new Checkbox("Use Date", false);

        dateCheck.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==1){
                    day.setEnabled(true);
                    month.setEnabled(true);
                    year.setEnabled(true);
                }else{
                    day.setEnabled(false);
                    month.setEnabled(false);
                    year.setEnabled(false);
                }
            }
        });




        Button confirmButton = new Button("Add Item");
        confirmButton.setBackground(new Color(127,255,127));


        confirmButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                TodoItem newItem = new TodoItem();
                newItem.setTitle(titleField.getText());
                newItem.setDescription(descriptionField.getText());

                if(dateCheck.getState()){
                    Calendar date = Calendar.getInstance();
                    date.clear(Calendar.HOUR);
                    date.clear(Calendar.MINUTE);
                    date.clear(Calendar.SECOND);
                    date.clear(Calendar.MILLISECOND);

                    date.set(Calendar.DATE, day.getSelectedIndex() + 1);
                    date.set(Calendar.MONTH, month.getSelectedIndex());
                    date.set(Calendar.YEAR, year.getSelectedIndex() + 1970);

                    newItem.setCalendar(date);
                }

                Main.list.add(newItem);
                Main.save();
                self.dispose();
            }
        });





        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;





        c.weightx = 0.1;
        panel.add(new Label("Title:"),c);
        c.gridx = 1;
        c.weightx = 1;
        panel.add(titleField,c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 0.1;
        panel.add(new Label("Description:"),c);
        c.gridx = 1;
        c.gridwidth = 4;
        c.weightx = 1;
        panel.add(descriptionField,c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.weightx = 0.1;
        panel.add(dateCheck,c);
        c.gridx = 1;
        c.gridwidth = 1;
        panel.add(month,c);
        c.gridx = 2;
        panel.add(day,c);
        c.gridx = 3;
        panel.add(year,c);

        c.gridy = 3;
        c.gridx = 0;
        c.weightx = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        panel.add(confirmButton,c);

        add(panel);
        setSize(400,300);
        setVisible(true);
    }
}
