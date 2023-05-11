package app.taskplanner.viewmodel;

import app.taskplanner.model.ModelFactory;
import app.taskplanner.viewmodel.taskplanner.TaskPlannerViewModel;
import java.io.IOException;

public class ViewModelFactory {

    private final TaskPlannerViewModel taskPlannerViewModel;
    public ViewModelFactory(ModelFactory modelFactory) throws IOException, ClassNotFoundException {
        taskPlannerViewModel = new TaskPlannerViewModel(modelFactory.getDataModel());
    }

    public TaskPlannerViewModel getTaskPlannerViewModel() {
        return taskPlannerViewModel;
    }
}
