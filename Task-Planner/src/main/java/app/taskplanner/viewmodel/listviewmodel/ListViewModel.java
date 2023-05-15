package app.taskplanner.viewmodel.listviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ListViewModel implements ViewModel {
    private ViewHandler viewHandler;
    private DataModel dataModel;

    public void init(ViewHandler viewHandler, DataModel dataModel) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
//        titles = viewHandler.listNotes();
//        listOfNotes.setItems(titles);
    }

    @Override
    public void closeWindow() {

    }
    void createNewNote(MouseEvent event) {
//        dataModel.addNote(newTitle.getText());
//        listOfNotes.setItems(viewHandler.listNotes());
//        listOfNotes.refresh();
    }


    void deleteSelectedNotes(MouseEvent event) {
//        List<String> selected = listOfNotes.getSelectionModel().getSelectedItems();
//        for (String title : selected) {
//            Note newNote = viewHandler.noteFromTitle(title);
//            dataModel.removeNote(newNote);
//        }
//        listOfNotes.refresh();
    }

    void openSelectedNote(MouseEvent event) {
//        List<String> selected = listOfNotes.getSelectionModel().getSelectedItems();
//        for (String title : selected) {
//            Note newNote = viewHandler.noteFromTitle(title);
//            viewHandler.openNote(newNote);
//        }
    }

}
