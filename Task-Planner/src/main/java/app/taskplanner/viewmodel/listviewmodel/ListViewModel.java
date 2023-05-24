package app.taskplanner.viewmodel.listviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.List;

public class ListViewModel implements ViewModel {

    private ObservableList<String> titles = FXCollections.observableArrayList();
    private DataModel dataModel;
    private ViewHandler viewHandler;

    public ListViewModel(DataModel dataModel) {
        this.dataModel = dataModel;
        initializeNotes();
    }

    public ListProperty<String> titlesProperty() {
        return new SimpleListProperty<>(titles);
    }

    private void initializeNotes() {
        List<Note> notes = dataModel.getNotes(); // Retrieve the notes from the model
        updateTitles(notes);
    }

    public void addNoteWithTitle(String title) {
        dataModel.addNote(title); // Add the new note to the model
        titles.add(title);
    }

    public void removeNoteAt(int index) {
        Note noteToRemove = dataModel.getNotes().get(index);
        dataModel.removeNote(noteToRemove); // Remove the note from the model
        titles.remove(index);
    }

    public void openNoteWithTitle(String title) {
        // Perform the necessary action when opening a note
        Note note = viewHandler.noteFromTitle(title);
        viewHandler.openNote(note);
    }

    public void refreshNotes() {
        List<Note> updatedNotes = dataModel.getNotes(); // Retrieve the updated notes from the model
        updateTitles(updatedNotes);
    }

    private void updateTitles(List<Note> notes) {
        titles.clear();
        for (Note note : notes) {
            titles.add(note.getTitle());
        }
    }

    @Override
    public void init(ViewHandler viewHandler, DataModel dataModel) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
    }

    @Override
    public void init(ViewHandler viewHandler, DataModel dataModel, Stage currentView) {

    }

    @Override
    public void closeWindow() {

    }
}
