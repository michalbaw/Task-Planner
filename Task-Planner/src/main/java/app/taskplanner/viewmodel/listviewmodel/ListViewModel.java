package app.taskplanner.viewmodel.listviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class ListViewModel implements ViewModel {

    private final ObservableList<NoteMetadata> notesMetadata = FXCollections.observableArrayList();
    private final DataModel dataModel;
    private final ViewHandler viewHandler;
    private ChangeModelService changeModelService;
    private NotificationService notificationService;

    public ListProperty<NoteMetadata> notesProperty() {
        return new SimpleListProperty<>(notesMetadata);
    }

    public ListViewModel(DataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        initializeNotes();
    }

    public void addNoteWithTitle(String title) {
        changeModelService.addNote(title);
        notificationService.notifyViewModels();
    }

    public void removeNoteAt(int index) {
        int key = notesMetadata.get(index).getKey();
        viewHandler.closeNote(key);
        changeModelService.removeNote(key);
        notificationService.notifyViewModels();
    }

    public void openNote(int key) {
        viewHandler.openNote(key);
    }

    public void closeAllNotes() {
        viewHandler.closeAllNotes();
    }

    private void initializeNotes() {
        List<NoteMetadata> notes = dataModel.getNotesMetadata();
        updateTitles(notes);
    }

    public void refreshNotes() {
        List<NoteMetadata> updatedNotes = dataModel.getNotesMetadata();
        updateTitles(updatedNotes);
    }

    private void updateTitles(List<NoteMetadata> notes) {
        this.notesMetadata.clear();
        this.notesMetadata.addAll(notes);
    }

    public void init(ChangeModelService changeModelService, NotificationService notificationService) {
        this.changeModelService = changeModelService;
        this.notificationService = notificationService;
    }
}
