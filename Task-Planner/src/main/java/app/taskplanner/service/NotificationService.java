package app.taskplanner.service;

import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import app.taskplanner.viewmodel.listviewmodel.ListViewModel;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;

import java.util.LinkedList;
import java.util.List;

public class NotificationService {
    ListViewModel listViewModel;
    BoardViewModel boardViewModel;
    List<NoteViewModel> noteViewModels;

    public void init(ListViewModel listViewModel, BoardViewModel boardViewModel) {
        this.listViewModel = listViewModel;
        this.boardViewModel = boardViewModel;
        noteViewModels = new LinkedList<>();
    }

    public void addNoteViewModel(NoteViewModel noteViewModel) {
        noteViewModels.add(noteViewModel);
    }

    public void removeViewModel(NoteViewModel noteViewModel) {
        noteViewModels.remove(noteViewModel);
    }

    public void notifyViewModels() {
        listViewModel.refreshNotes();
        boardViewModel.refreshNotes();
    }

    public void notifyViewModels(int key) {
        listViewModel.refreshNotes();
        boardViewModel.refreshNotes();
        for (NoteViewModel nvm : noteViewModels) {
            if (nvm.getKey() == key) {
                try {
                    nvm.refresh();
                }
                catch(NullPointerException e){
                    removeViewModel(nvm);
                }
            }
        }
    }

}
