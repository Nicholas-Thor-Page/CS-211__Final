import javax.swing.*;
import java.awt.*;
public class ListView extends ScrollPane{
    JPanel panel;
    TodoList list;
    public ListView(TodoList list){
        panel = new JPanel();
        this.list = list;
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setFont(panel.getFont().deriveFont(18.0f));
        for(TodoItem item : list)
            panel.add(new TodoButton(item));
        if(list.size() == 0)
            panel.add(new Label("No Items"));
        this.add(panel);
    }
}
