package app.taskplanner.model;

import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SimpleObservableList<T> extends ModifiableObservableListBase<T> {
    private final List<T> delegate = new ArrayList<>();

    public T get ( int index){
        return delegate.get(index);
    }
    public int size () {
        return delegate.size();
    }
    protected void doAdd ( int index, T element){
        delegate.add(index, element);
    }
    protected T doSet ( int index, T element){
        return delegate.set(index, element);
    }
    protected T doRemove ( int index){
        return delegate.remove(index);
    }
}
