package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.application.model.Model;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.application.Application;
import seedu.application.model.application.Status;

public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_SUCCESS = "Listed all applications";

    @Override
    public CommandResult execute(Model model) {
        int archived = 0;
        int rejected = 0;
        int interviews = 0;
        int offered = 0;
        int pending = 0;
        requireNonNull(model);
        ObservableList<Application> currentList = model.getAllApplicationsInBook();
        int totalApplications = currentList.size();
        for (Application app : currentList) {
            if (app.isArchived()) {
                archived++;
            }
            if (app.getStatus().equals(Status.REJECTED)) {
                rejected++;
            }
            if (app.getStatus().equals(Status.OFFERED)) {
                offered++;
            }
            if (app.getStatus().equals(Status.PENDING)) {
                pending++;
            }
            if (app.hasInterview()) {
                interviews++;
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
