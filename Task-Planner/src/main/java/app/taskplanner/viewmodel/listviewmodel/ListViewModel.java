package app.taskplanner.viewmodel.listviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.viewmodel.Handler;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListViewModel implements ViewModel {

    private ObservableList<NoteMetadata> notesMetadata = FXCollections.observableArrayList();
    private DataModel dataModel;
    private ViewHandler viewHandler;

    public ListViewModel(DataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        initializeNotes();
    }

    public ListProperty<NoteMetadata> notesProperty() {
        return new SimpleListProperty<>(notesMetadata);
    }

    private void initializeNotes() {
        List<NoteMetadata> notes = dataModel.getNotesMetadata(); // Retrieve the notes from the model
        updateTitles(notes);
    }

    public void addNoteWithTitle(String title) {

        viewHandler.addNoteWithTitle(title);
    }

    public void removeNoteAt(int index) {
        viewHandler.removeNote(notesMetadata.get(index).getKey());
        refreshNotes();
    }
    public void openNote(Note note) {
        viewHandler.openNote(note);
    }
    public void openWithKey(int key)
    {
        //viewHandler.openNote(key);
        viewHandler.openNote(getNote(key));
    }
    public void refreshNotes() {
        List<NoteMetadata> updatedNotes = dataModel.getNotesMetadata(); // Retrieve the updated notes from the model
        updateTitles(updatedNotes);
    }
    public Note getNote(int key){
        Note note = null;
        try {
            note = dataModel.openNote(key);
        }
        catch (IOException ioException)
        {
            System.err.println("KeinProblemHerrMichal");
        }
        return note;
    }
    private void updateTitles(List<NoteMetadata> notes) {
        this.notesMetadata.clear();
        this.notesMetadata.addAll(notes);
    }

    @Override
    public void init(Handler viewHandler, DataModel dataModel) {
        this.viewHandler = (ViewHandler) viewHandler;
        this.dataModel = dataModel;
    }

    @Override
    public void init(Handler viewHandler, DataModel dataModel, Stage currentView) {

    }

    @Override
    public void closeWindow() {

    }
}
