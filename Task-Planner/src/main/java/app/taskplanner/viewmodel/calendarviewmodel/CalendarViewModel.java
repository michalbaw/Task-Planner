package app.taskplanner.viewmodel.calendarviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.view.calendarview.CalendarViewController;
import app.taskplanner.viewmodel.ViewHandler;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarViewModel {
    private DataModel dataModel;
    private ViewHandler viewHandler;
    private ChangeModelService changeModelService;
    private NotificationService notificationService;
    private CalendarViewController calendarViewController;

    public CalendarViewModel(DataModel dataModel,ViewHandler viewHandler){
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
    }
    public void init(ChangeModelService changeModelService, NotificationService notificationService) {
        this.changeModelService = changeModelService;
        this.notificationService = notificationService;
    }
    public void feelCalendarViewController(CalendarViewController calendarViewController){
        this.calendarViewController = calendarViewController;
    }
    public Map<Integer, List<Note>> getNotesForCurrentMonth(ZonedDateTime dateFocus) {
        List<NoteMetadata> allNoteList = dataModel.getNotesMetadata();
        List<Note> noteList;
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        allNoteList = allNoteList.stream().filter(n -> n.getDate() != null).toList();
        allNoteList = allNoteList.stream().filter(n -> n.getDate().getYear() == year && n.getDate().getMonth().getValue() == month).toList();


            noteList = allNoteList.stream().map(n -> {
                try {
                    return dataModel.getNote(n.getKey());
                } catch (IOException e) {
                    System.err.println("jeżeli tutaj się zepsuje, to po tym wszystkim rzucam studia");
                    return null;
                }

            }).toList();

        return setNoteMap(noteList);
    }
    private Map<Integer, List<Note>> setNoteMap(List<Note> noteList) {
        Map<Integer, List<Note>> notesMap = new HashMap<>();

        for (Note Note: noteList) {
            int NoteDate = Note.getMetadata().getDate().getDayOfMonth();
            if(!notesMap.containsKey(NoteDate)){
                notesMap.put(NoteDate, List.of(Note));
            } else {
                List<Note> manyNotesInOneDay = notesMap.get(NoteDate);

                List<Note> newList = new ArrayList<>(manyNotesInOneDay);
                newList.add(Note);
                notesMap.put(NoteDate, newList);
            }
        }
        return  notesMap;
    }
    public void openNoteByKey(int key){
        viewHandler.openNote(key);
    }

    public void refreshCalendar(){
        calendarViewController.refresh();

    }
}
