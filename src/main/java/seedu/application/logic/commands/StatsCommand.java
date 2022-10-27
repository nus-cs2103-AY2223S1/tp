package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.application.model.Model;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.application.Application;

public class StatsCommand {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_SUCCESS = "Listed all applications";

    private int archived;
    private int rejected;
    private int interviews;
    private int offered;
    private int pending;
    private int totalApplications;

    StatsCommand() {
        archived = 0;
        rejected = 0;
        interviews = 0;
        offered = 0;
        pending = 0;
        totalApplications = 0;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Application> currentList = model.getAllApplicationsInBook();
        totalApplications += currentList.size();
        for (Application app : currentList) {
            if (app.isArchived()) {
                archived += 1;
            }
            if (app.getStatus().toString.equals())
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
