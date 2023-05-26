package app.taskplanner.viewmodel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class SimpleNote {
    private static Integer count=0;
    private final IntegerProperty id;
    private final DoubleProperty x =new SimpleDoubleProperty(0);
    private final DoubleProperty y = new SimpleDoubleProperty(0);
    private final StringProperty title = new SimpleStringProperty("");
    private final StringProperty content = new SimpleStringProperty("");
    private final DoubleProperty height = new SimpleDoubleProperty(0);
    private final DoubleProperty width = new SimpleDoubleProperty(0);
    private final ListProperty<NoteTasks> tasks = new SimpleListProperty<>();

    public SimpleNote(){
        id = new SimpleIntegerProperty(count++);
    }
    public SimpleNote(String title, String content, ObservableList<NoteTasks> tasks, IntegerProperty id){
        this();
        setTitle(title);
        setContent(content);
        setTasks(tasks);
    }
    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public double getHeight() {
        return height.get();
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public ObservableList<NoteTasks> getTasks() {
        return tasks.get();
    }

    public ListProperty<NoteTasks> tasksProperty() {
        return tasks;
    }

    public void setTasks(ObservableList<NoteTasks> tasks) {
        this.tasks.set(tasks);
    }


    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj == null)
            return false;
        if(obj.getClass() != this.getClass())
            return false;
        SimpleNote sNote = (SimpleNote) obj;
        if(sNote.content!=this.content)
            return false;
        if(sNote.title != this.title)
            return false;
        if(sNote.tasks != this.tasks)
            return false;
        return true;
    }
}
