package app.taskplanner.viewmodel.listviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
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
        List<NoteMetadata> notes = dataModel.getNotesMetadata(); // Retrieve the notes from the model
        updateTitles(notes);
    }

    public void addNoteWithTitle(String title) {
        try {
            dataModel.addNote(title); // Add the new note to the model
        }
        catch (IOException ioException){
            System.err.println("yyyy");
        }
        titles.add(title);
    }

    public void removeNoteAt(int index) {
        NoteMetadata noteToRemove = dataModel.getNotesMetadata().get(index);
        try{

        dataModel.removeNote(noteToRemove.getKey()); // Remove the note from the model
        }
        catch (IOException ioException)
        {
            System.err.println("deletion? nope.");
        }
        titles.remove(index);
    }

    public void openNoteWithTitle(String title) {
        // Perform the necessary action when opening a note
        Note note = viewHandler.getNoteFromID(0);
        viewHandler.openNote(note);
    }

    public void refreshNotes() {
        List<NoteMetadata> updatedNotes = dataModel.getNotesMetadata(); // Retrieve the updated notes from the model
        updateTitles(updatedNotes);
    }

    private void updateTitles(List<NoteMetadata> notes) {
        titles.clear();
        for (NoteMetadata note : notes) {
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
