package app.taskplanner.view;

import app.taskplanner.model.DataModel;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;

public interface ViewController {
    public void init(ViewModel viewModel);
}
