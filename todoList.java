import java.util.ArrayList;
import java.util.Calendar;

public class TodoList extends ArrayList<TodoItem>{
    @Override
    public boolean add(TodoItem item){
        int i = 0;
        if(item.getCalendar() != null)
            while(i < this.size() && item.getCalendar().after(this.get(i).getCalendar()))
                i++;
        super.add(i, item);
        return true;
    }

    public TodoList filterByDate(Calendar start, Calendar end){
        TodoList out = new TodoList();


        for(TodoItem item : this)
            if(item.getCalendar() != null && item.getCalendar().after(start) && item.getCalendar().before(end))
                out.add(item);


        return out;
    }
}
